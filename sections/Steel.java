
package sections;
import enums.GRADES;

/**
 * Define some concrete parameters of the steel in the reinforced beams
 */
public class Steel {
    
    // Maximum resistance characteristic of steel in compression and in tension.
    private double fyk;
    // Steel Elasticity Module (Young)
    private double Es = 210000;
    // Strain of the steel at the Rupture
    private double eyk;
    // Maximum permissible deformation for the steel
    private double esu = 1.0/100;



    /**
     * Constructor for the Class Steel that uses its 
     * Characterist resistance
     * @param fyk double - Characteristic resistance [MPa]
     */
    public Steel(double fyk){
        if (fyk <= 0){
            throw new IllegalArgumentException();
        }
        this.fyk = fyk;
        this.eyk = fyk/Es;
    }



    /**
     * Constructor for the Class Steel that uses its 
     * respective ENUMs
     * @param Grade.grade Enum - Grade of the steel used
     */
    public Steel(GRADES.Grade aco){
        this(aco.getFyk());
        this.eyk = fyk/Es;
    }



    /**
     * @return double return the Es
     */
    public double getEs() {
        return Es;
    }

    /**
     * @return double return the fykk
     */
    public double getfyk() {
        return fyk;
    }

    /**
     * @return double return the eykk
     */
    public double geteyk() {
        return eyk;
    }

    /**
     * @return double return the eykk
     */
    public double getesu() {
        return esu;
    }



    @Override
    public String toString() {
        return String.format(
        "Steel Parameters: \n" +
        "  fyk: \t" + fyk     + "MPa, \n" +
        "  eyk: \t" + eyk*100 + "%%,  \n" +
        "  esu: \t" + esu*100 + "%%,  \n" +
        "  Es:  \t" + Es      + "MPa.");
    }

}