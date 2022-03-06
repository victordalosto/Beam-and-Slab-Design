
package sections;
import java.util.ArrayList;
import calculations.Resistance;

/**
 * Class used to calculate slab sections
 */
public class Slab extends ConcreteSection {


    /**
     * Constructor with the properties of the Rectangular Section.
     * @param d - Effective Height of the rectangular slab [millimeter].
     * @param fck - Concrete compressive Strength [MPa].
     * It is assumed that the standard steel grade CA50 (most commonly used) is used.
     */
    public Slab(double d, double fck){
        if (d <=0 || fck <= 0) {
            throw new IllegalArgumentException("Input values must be > 0");
        }
        this.b = 1000;
        this.d = d;
        this.fck = fck;

        // Default steel property CA-50 is used if not inserted in the constructor.
        if (this.steel == null){
            this.steel = new Steel(enums.GRADES.Grade.CA50); // Most commom type of steel used in Civil Construction.
        }
    }



    /**
     * Constructor with the properties of the Rectangular Section.
     * @param d - Effective Height of the rectangular slab [millimeter].
     * @param Concrete - enums.Grades.Concrete Classe of concrete according to Brazilian Code.
     */
    public Slab(double d, enums.GRADES.Concrete concrete){
        this(d, concrete.getFck());
    }


    
    /**
     * Constructor with the properties of the Rectangular Section.
     * @param d - Effective Height of the rectangular slab [millimeter].
     * @param Concrete - Classe of concrete according to Brazilian Code.
     * @param Steel - Grade of the Steel used as reinforcement in concrete
     */
    public Slab(double d, enums.GRADES.Concrete concrete, enums.GRADES.Grade steel){
        this(d, concrete.getFck());
        this.steel = new Steel(steel);
    }


    
    /**
     * Constructor with the properties of the Rectangular Section.
     * @param d - Effective Height of the rectangular slab [millimeter].
     * @param fck - Concrete compressive Strength [MPa]
     * @param Steel - Grade of the Steel used as reinforcement in the concrete
     */
    public Slab(double d, double fck, enums.GRADES.Grade steel){
        this(d, fck);
        this.steel = new Steel(steel);
    }


    
    /**
     * Constructor with the properties of the Rectangular Section.
     * @param d - Effective Height of the rectangular slab [millimeter].
     * @param Concrete - Classe of concrete according to Brazilian Code.
     * @param Steel - Grade of the Steel used as reinforcement in concrete
     */
    public Slab(double d, enums.GRADES.Concrete concrete, Steel steel){
        this(d, concrete.getFck());
        this.steel = steel;
    }


    
    /**
     * Constructor with the properties of the Rectangular Section.
     * @param d - Effective Height of the rectangular slab [millimeter].
     * @param fck - Concrete compressive Strength [MPa]
     * @param Steel - Grade of the Steel used as reinforcement in concrete
     */
    public Slab(double d, double fck, Steel steel){
        this(d, fck);
        this.steel = steel;
    }


    /**
    * 
    * End of constructors
    *
    */

    
    /**
     * 
     * @param mk - Moment force applied in the Slab [N.mm]
     * @param code - Defines the code parameters and coeficients
     */
    public void FlexuralResistance(double mk, standards.Code code){
        ArrayList<Double> reinforcementAreas = Resistance.Flexural(mk, new Slab(this.d, this.fck), code, this.steel);
        this.As = reinforcementAreas.get(0);
        this.As2 = reinforcementAreas.get(1);
    }


    @Override
    public String toString() {
        String str =  
        "*Slab Dimensions: " + "\n" +
        super.toString();
        return str;
    }
}