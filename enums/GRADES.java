
package enums;

/**
 * Defines some pre-build grades that can be used to 
 * define the types of steel and concrete.
 */
public class GRADES {

    /**
     * Defines the types of Steel that are used as concrete reinforcement
     */
    public enum Grade {
        // CAXX defined as 'Concreto Armado', with XX0 compressive/tension resistance
        CA25(250), // Wire
        CA50(500), // Bars
        CA60(600); // Smooth bars

        // Characteristic compressive/tension resistance of the Steel
        private double fyk;

        // GRADES.Grade Construtor
        Grade(double fyk){
            this.fyk = fyk;
        }

        // Method that gets the compressive/tension resistance of the Steel
        public double getFyk(){
            return this.fyk;
        }
    }

    

    /**
     * Defines the types of concrete that are adopted by some standard
     */
    public enum Concrete {
        // Classes defined as CXX - Concrete with XX MPa compressive resistance
        C10(10.0), C15(15.0), C20(20.0),
        C25(25.0), C30(30.0), C35(35.0),
        C40(40.0), C45(45.0), C50(50.0),
        C55(55.0), C60(60.0), C65(65.0),
        C70(70.0), C75(75.0), C80(80.0),
        C85(85.0), C90(90.0);

        // Characteristic compressive strength of concrete
        private double fck;

        // GRADES.Concrete Construtor
        Concrete (double fck){
            this.fck = fck;
        }
        
        // Method that gets the compressive strength of concrete
        public double getFck(){
            return this.fck;
        }
    }
}