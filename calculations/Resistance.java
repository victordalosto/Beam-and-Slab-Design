
package calculations;

import java.util.ArrayList;

import sections.ConcreteSection;
import sections.Steel;
import standards.*;
import utils.ConcreteMath;

/**
 * Class that performs:
 * flexural and shear strength calculations of Beams and Slabs
 */
public class Resistance{

    public static ArrayList<Double> Flexural(double mk, ConcreteSection section, Code code, Steel steel) {
        ArrayList<Double> reinforcementsArea = new ArrayList<Double>();

        // Update all parameters for the code
        code.setFck(section.getFck());

        // Beam Physical Characteristics
        double bw = section.getWidth();  // Section Width
        double d  = section.getHeight(); // Section effect height
        double d2 = 50; // Pre-estabeleced

        // Defines Mechanical Characteristics
        double fcd = section.getFck()  / code.getGc(); // Design Concrete Strength.
        double fyd = steel.getfyk() / code.getGs(); // Design Steel Strength.
        double esu = steel.getesu(); // Maximum admissible steel tension deformation.

        // Define some others parameters
        double Msd = mk * code.getGf(); // Design Bending Moment applied on the Beam or Slab.
        double Es = steel.getEs(); // Steel resistance module.
        double Md = 0; // Effective bending moment that can be resisted with simple reinforcement.
        double xk = 0; // Neutral axis of the section.
        double CG = 0; // Resultant (CG) of the compression forces applied.
        double es = 0; // Strain of the steel reinforcement under tension
        double es2 = 0; // Strain  of the steel reinforcement under compression
        double ecu = code.getecu();  // Concrete strain at the Ultimate Limit State of Rupture.


        // Parameters used for calculations of the Concrete Diagram under compression according to code
        double ec = 0; // Strain deformation of the concrete
        double Ae = 0; // Specific area of the concrete under compression
        double deformations[]; // array containing [e1-strain of the concrete at the parabola area, e2-strain at the constant area]

        // Defines with Code is beign used:
        if (code instanceof standards.BrazilianCode){
            // Define some parameters specific for the Brazilian Code
            double n = ((BrazilianCode) code).getN(); // Parameter related to the parable
            double ec2 = ((BrazilianCode) code).getec2();  // Concrete strain at the rupture by compression.
            // Defines possible limits for the neutral axis
            double xi = 0;
            double xf = Math.min(code.getLN()*d, section.getHeight());
            // Loop that calculates section forces for given Neutral Axis and converges new values to equilibrium.
            while(Math.abs(xi-xf) > 0.001){
                xk = ConcreteMath.convergenceMethod(xi, xf);
                deformations = ConcreteMath.defineStrain(xk, d, ecu, ec2, esu);
                ec = deformations[0] + deformations[1];
                Ae = ConcreteMath.obtainArea(deformations[0], deformations[1], ec2, n);
                CG = ConcreteMath.obtainCG(ec, deformations[0], deformations[1], ec2, n);
                Md = 0.85*fcd*bw*(Ae/ec)*xk*(d-(CG/ec)*xk);
                if (Md > Msd) {
                    xf = xk;
                } else {
                    xi = xk;
                }
                CG = CG/ec*xk;

            }

        } else 
        if (code instanceof standards.EuropeanCode) {
            // Define some parameters specific for the EuropeanCode - Eurocode2
            double ac = ((EuropeanCode) code).getAc(); // Parameter related to the parable
            double LN = ((EuropeanCode) code).getLN(); // Concrete strain at the Ultimate Limit State of Rupture.
            double lambda = ((EuropeanCode) code).getLambda();  // Concrete strain at the rupture by compression.
            // Calculation of the Retangular section
            xk = Math.min((d-Math.sqrt(Math.pow(d,2)-(2*Msd)/(ac*fcd*bw)))/lambda,  LN*d);
            ec = Math.min(esu*xk/(d-xk), ecu);
            CG = lambda/2*xk;
            Md = ac*fcd*bw*lambda*xk*(d-lambda/2*xk);
        }
        // Obtain Reinforcements Strains
        es = ec*d/xk - ec;
        es2 = ec*(xk - d2)/xk;
        // Obtain Reinforcements Stress
        double fs = Math.min(fyd, Es*es);
        double fs2 = Math.min(fyd, Es*es2);
        
        double Md2 = Msd - Md;
        // Avoid numerical errors
        if (Md2 <= 50000){
            Md2 = 0;
            Md += Md2;
        }

        // Obtain reinforcements area
        double As2 = Md2/(fs2*(d-d2));
        double As = Md/(fs*(d-CG)) + Md2/(fs*(d-d2));


        // Round value to avoid Numerical Computacional errors
        As  = Math.round(As*10000)/10000;
        As2 = Math.round(As2*10000)/10000;

        // Arbitrary value of Neutral Axis
        reinforcementsArea.add(As);
        reinforcementsArea.add(As2);
        return reinforcementsArea;
    }
    
}
