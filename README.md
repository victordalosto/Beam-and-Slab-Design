## Beam and Slab Design
This is a library developed in Java that can be used to design reinforced concrete elements of the type: Beams and Slabs.
The methods presented here allow the design of the concrete sections according to the Brazilian Standard <a href = "https://www.abntcatalogo.com.br/norma.aspx?Q=U3JENHZueEVFaDczYjdKbkFJWFUyOTIrczVUWnJnSnhsRlJJbTN3WW50UT0=">ABNT NBR 6118:2014</a>, and according to the European standard <a href = "https://en.wikipedia.org/wiki/Eurocode_2:_Design_of_concrete_structures"> Eurocode 2.</a>

_
## Routine
 The routine presented here consists of:
* Defining the moment effort being applied to the element. Beam and slab elements are predominantly stressed in bending.
* Define a concrete class that will be used for the design of the beam or the slab. Knowing that this is an iterative process, some enums are established with some pre-defined classes, which can be changed posteriourly in a refinement process.
* Similarly, the steel class that will be used in the beam to be dimensioned must be previously chosen.
* It's then defined the standard that will be used to design the structural element. This library contains two standards that can already be selected: the Brazilian standard (NBR 6118), and the European standard (Eurocode2).
* Then just call the method of the instance that performs the design process. The method then assigns to its instance variable, the areas found with the dimensioning.

_
## Example of usage
The repository contains a predefined class Test.java showing the use of the methods to perform the design:

```java
// The implementation of these routines, for this library, may be used as:
class {
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
```
 
