
package utils;

/**
 * The methods developed here are my own authorship @Author VictorDalosto,
 * and do not use the simplifications commonly used in practice.
 */
public class ConcreteMath {
    
    // Gauss node point and Weight constants from the Gaussian Quadrature for 5 points integral solution.
        // Defines the Gaussian Points of evaluation of the function
        private static final double X1 = 0;
        private static final double X2 = + 1.0/3 * Math.sqrt(5.0 - 2.0*Math.sqrt(10.0/7));
        private static final double X3 = - 1.0/3 * Math.sqrt(5.0 - 2.0*Math.sqrt(10.0/7));
        private static final double X4 = + 1.0/3 * Math.sqrt(5.0 + 2.0*Math.sqrt(10.0/7));
        private static final double X5 = - 1.0/3 * Math.sqrt(5.0 + 2.0*Math.sqrt(10.0/7));
        // Defines the Gaussian Weight of evaluation of the function    
        private static final double W1 = 128.0/225;
        private static final double W2 = (322.0 + 13.0*Math.sqrt(70.0))/900.0;
        private static final double W3 = (322.0 + 13.0*Math.sqrt(70.0))/900.0;
        private static final double W4 = (322.0 - 13.0*Math.sqrt(70.0))/900.0;
        private static final double W5 = (322.0 - 13.0*Math.sqrt(70.0))/900.0;



    /**
     * Obtains the Compression Area of the Concrete Diagram, According to the Brazilian Code NBR 6118:2014 - Item 8.2.10.1.
     * @param e1  double - Strain of the concrete while in the parable range.
     * @param e2  double - Strain of the concrete while in the constante range.
     * @param ec2 double - Constant of concrete strain at the rupture by compression.
     * @param n   double - Constant of the Diagram, defined by the code.
     * @return    double - The <strong>specific</strong> area of the bilinear Compression Concrete diagram.
    */
    public static double obtainArea(double e1, double e2, double ec2, double n){
        // Code expression  e = 1 - (1- ec/ec2)^n
        // Gaussian Quadrature Expression  Pn(x) = ((b-a)/2) * sum{w* f[(b-a)/2*xi + (b+a)/2]}
        double specificArea;
        // Expression to calculate each sum wi*f(x)i
        specificArea =  (e1/2) * W1*(1-Math.pow((1-((e1/2)*X1+(e1/2))/ec2), n));
        specificArea += (e1/2) * W2*(1-Math.pow((1-((e1/2)*X2+(e1/2))/ec2), n));
        specificArea += (e1/2) * W3*(1-Math.pow((1-((e1/2)*X3+(e1/2))/ec2), n));
        specificArea += (e1/2) * W4*(1-Math.pow((1-((e1/2)*X4+(e1/2))/ec2), n));
        specificArea += (e1/2) * W5*(1-Math.pow((1-((e1/2)*X5+(e1/2))/ec2), n));
        // Add the constant part of the Concrete Diagram
        specificArea += e2;
        return specificArea;
    }



    /**
     * Obtains the Gravity Center of the Concrete Diagram.
     * @param ec  double - Strain of the concrete for the applied force
     * @param e1  double - Strain of the concrete while in the parable range.
     * @param e2  double - Strain of the concrete while in the constante range.
     * @param ec2 double - Constant of concrete strain at the rupture by compression.
     * @param n   double - Constant of the Diagram, defined by the code.
     * @return    double - The <strong>specific</strong> center point of the resultant of the Compression Concrete diagram.
    */
    public static double obtainCG(double ec, double e1, double e2, double ec2, double n){
        double Ae = obtainArea(e1, e2, ec2, n);
        double CG;
        // Centroid Calculation  CG = int(x*A)/int(A)
        // Calculates the part: int(x*a)
        // Calculates the diagram part of the gravity center
        CG =  (e1/2) * W1*((e1/2)*X1+(e1/2))*(1-Math.pow((1-((e1/2)*X1+(e1/2))/ec2), n));
        CG += (e1/2) * W2*((e1/2)*X2+(e1/2))*(1-Math.pow((1-((e1/2)*X2+(e1/2))/ec2), n));
        CG += (e1/2) * W3*((e1/2)*X3+(e1/2))*(1-Math.pow((1-((e1/2)*X3+(e1/2))/ec2), n));
        CG += (e1/2) * W4*((e1/2)*X4+(e1/2))*(1-Math.pow((1-((e1/2)*X4+(e1/2))/ec2), n));
        CG += (e1/2) * W5*((e1/2)*X5+(e1/2))*(1-Math.pow((1-((e1/2)*X5+(e1/2))/ec2), n));
        // Calculates the constant part of the Gravity Center
        CG += (e2/2) * W1*(((ec-e1)/2)*X1+((ec+e1)/2));
        CG += (e2/2) * W2*(((ec-e1)/2)*X2+((ec+e1)/2));
        CG += (e2/2) * W3*(((ec-e1)/2)*X3+((ec+e1)/2));
        CG += (e2/2) * W4*(((ec-e1)/2)*X4+((ec+e1)/2));
        CG += (e2/2) * W5*(((ec-e1)/2)*X5+((ec+e1)/2));
        // Calculate the part: int(A)
        CG = CG/Ae;
        // Invert the position to start at the top of the Diagram.
        CG = ec - CG;
        return CG;
    }



    /**
     * Find the concrete strain in the Diagram at the Parabola range(e1), and at the Constant range (e2).
     * @param xk  double - Value of the Neutral Axis for the applied load.
     * @param d   double - Value of the effective height of the Beam or Slab.
     * @param ecu double - Value of the concrete strain at the Ultimate Limit State of Rupture.
     * @param ec2 double - Constant of concrete strain at the rupture by compression.
     * @param esu double - Maximum admissible value of strain for the Steel.
     * @return double[2] - Concrete Strain e1 and e2 for the specified Neutral Axis.
     */
    public static double[] defineStrain(double xk, double d, double ecu, double ec2, double esu){
        // Defines the concrete Strain for the Neutral Axis specified
        double ec = Math.min(esu*xk/(d-xk), ecu);
        // Defines the concrete Strain at the Parabola Range
        double e1 = Math.min(ec, ec2);
        // Defines the concrete Strain at the constant Range
        double e2;
        if (e1 < ec2) {
            e2 = 0;
        } else {
            e2 = ec - ec2;
        }
        double deformations[] = {e1, e2};
        return deformations;
    }



    /**
     * The method used to converge the solution.
     * At this moment, it's used only the Bisection Method
     * @param xi double - Bottom limit evaluated
     * @param xf double - Upper limit evaluated
     * @return   double - Return a value inside the specified limit.
     */
    public static double convergenceMethod(double xi, double xf){
        // Bissection method used
        return (xi+xf)/2;
    }

}   