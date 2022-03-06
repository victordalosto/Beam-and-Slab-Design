
package sections;

/**
 * Class that is used to define the properties of the structural elements 
 * of the Reinforced Concrete element has a rectangular transverse dimension, typical in beams and slabs.
 */
public class ConcreteSection {

    // Dimensions of the Section
    double b; // Width of the element in millimeter
    double d; // Height of the element in millimeter
    double fck;  // Code used to calculate resistances
    Steel steel; // Code used to calculate resistances
    double As;   // Area of steel reinforcement in tension
    double As2;  // Area of steel reinforcement in compression


    
    /**
     * Change the Width value of the section
     * @param b - Width of the rectangular section [millimeter].
     */
    public void setWidth(double b){
        if (b <= 0) {
            throw new IllegalArgumentException("Input values must be > 0");
        }
        this.b = b;
    }



    /**
     * Change the effective Height value of the section
     * @param d - Effective Height of the rectangular section [millimeter].
     */
    public void setHeight(double d){
        if (d <= 0) {
            throw new IllegalArgumentException("Input values must be > 0");
        }
        this.d = d;
    }



    /**
     * Updates the concrete fck of the section.
     * @param fck - Value of Compression Concrete Resistance in MPa
     */
    public void setFck(double fck){
        if (fck <= 0) {
            throw new IllegalArgumentException("Input values must be > 0");
        }
        this.fck = fck;
    }


    /** @return double return the section width - b */
    public double getWidth() {
        return this.b;
    }


    /** @return double return the section eff. Height - d */
    public double getHeight(){
        return this.d;
    }


    /** @return double return the section fck - compression resistance */
    public double getFck(){
        return this.fck;
    }


    /**@return double return the section Reinforcement area in tension */
    public double getAs(){
        return this.As;
    }


    /** @return double return the section Reinforcement area in Compression */
    public double getAs2(){
        return this.As2;
    }


    @Override
    public String toString() {
        String str = 
        "  Width-b  =\t" + this.b + "mm, \n" +
        "  Height-d =\t" + this.d + "mm, \n" +
        "  fck =     \t" + this.fck + "MPA, \n" +
        "  Area-As  =\t" + this.As/100 + "cm², \n" + 
        "  Area-As2 =\t" + this.As2/100 + "cm2. \n";
        return str;
    }
}