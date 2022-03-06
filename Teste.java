
import enums.GRADES.*;
import sections.*;
import standards.*;

/**
 * This class presents Examples of how this library can be used to design sections of:
 * (1) Beam, and (2) Slabs of reinforced Concrete element.
 * Sections designed in Civil Engineering relays on the definition of the:
 * (i)   Force applied on the Element;
 * (ii)  The specification of the concrete that will be used;
 * (iii) The specification of the type of steel reinforcement that will be used;
 * (iv)  The Code Design that will be used (Controlled by the country regulatory standards)
 * (v)   Calculating and designing the element to assure that its section Resistance is greater and will sustain the forces applied.
 */
public class Teste {

    public static void main(String[] args) {

        // The implementation of these routines, for this library, may be used as:
            // Definition of the characteristic moment force applied in the Section
            double momentForce = 70*Math.pow(10, 6);
            // Definition of the type of Concrete of the Section (Enums)
            Concrete concrete = Concrete.C20;
            // Definition of the type of steel that will be used as reinforcement (Enums)
            Grade steelCA50 = Grade.CA50;

            // Specification of some Civil Engineer Standard that will be used
            // This library contains an implementation of 2 codes
            BrazilianCode NBR6118 = new BrazilianCode(); // Example1: Calculating using the Brazilian Code - NBR 6118
            EuropeanCode EuroCode2 = new EuropeanCode(); // Example2: Calculating using the European Code - Eurocode2
        
            // Application of the Method in a case of a Beam Element.
                // Creates the Beam that is used in the calculation
                // There are 6 different types of constructors used for the instantiation of this class
                Beam beamV1 = new Beam(150.0, 500.0, concrete, steelCA50); // Equivalent to: Beam beamV1 = new Beam(150.0, 500.0, 20, 500);
                beamV1.FlexuralResistance(momentForce, NBR6118); // Calculates the Reinforcement for the Beam
                System.out.println(beamV1); // Print Characteristic of the beam after Design

                // The same Beam can be designed using the European Code:
                beamV1.FlexuralResistance(momentForce, EuroCode2); // Calculates the Reinforcement for the Beam
                System.out.println(beamV1); // Print Characteristic of the beam after Design

            // Application of the Method in a case of a Slab element.
                // Creates the slab:
                Slab slabL1 = new Slab(150, concrete, steelCA50);
                slabL1.FlexuralResistance(5.0*Math.pow(10,6), NBR6118);
                System.out.println(slabL1);
    }
}