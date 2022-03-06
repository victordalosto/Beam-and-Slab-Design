
package standards;

/**
 * Class that contains parameters of European EuroCode 2.
 * Handles all parameters and safety coefficients related to Structural Concrete Calculation.
 */
public class EuropeanCode extends Code{

    // Compressive strength of concrete
    private double fck;

    // Neutral Axis limit
    private double LN;

    // Flexural parameters
    private double ac;
    private double lambda;
    private double ecu;


    // Construtor that defines its Safety Factor coeffeficients (SuperClass Code)
    public EuropeanCode() {
        this.gc = 1.4;
        this.gf = 1.4;
        this.gs = 1.15;
    }

    /**
     * Updates the concrete fck and all its subsequent parameters.
     * @param fck - Value of Compression Concrete Resistance in MPa
     */
    public void setFck(double fck){
        if (fck <= 0) {
            throw new IllegalArgumentException();
        // Maximum Concrete Resistance according to Code = 90 MPa.
        } else if (fck > 90) {
            fck = 90;
        }
        this.fck = fck;
        updateCode();
    }



    // Method that updates all code parameters used in the calculation
    private void updateCode(){
        this.ecu = 3.5/1000;
        if (this.fck <= 50){
            this.ac = 0.85;
            this.lambda = 0.8;
            this.LN = 0.45;
        } else {
            this.ac = 0.85*(1-((fck-50)/200));
            this.lambda = 0.8-((fck-50)/400);
            this.LN = 0.35;
        }
    }



    /**
     * Update the Safety factor Coefficients
     * @param gc - Coefficient safety factor for concrete
     * @param gs - Coefficient safety factor for steel
     * @param gf - Coefficient safety factor for Loads
     */
    public void setCoefficients(double gc, double gs, double gf){
        if (gc < 1 || gs < 1 || gf < 1){
            throw new IllegalArgumentException();}
        this.gc = gc;
        this.gs = gs;
        this.gf = gf;
    }



    @Override
    public String toString() {
        return String.format(
        "Concrete Parameters according to EuroCode 2: \n" + 
        "  fck    =\t" + Math.round(this.fck * 1000.0)    / 1000.0 + "MPa,\n" +
        "  ac     =\t" + Math.round(this.ac * 1000.0)     / 1000.0 + ",\n" +
        "  lambda =\t" + Math.round(this.lambda * 1000.0) / 1000.0 + ".\n");
    }



    /**
     * @return double - return the compressive strength of concrete - fck
     */
    public double getFck() {
        return fck;
    }


    /**
     * @return double - return the gc coefficient factor for Concrete.
     */
    public double getGc() {
        return gc;
    }


    /**
     * @return double - return the gs coefficient factor for Steel.
     */
    public double getGs() {
        return gs;
    }

    
    /**
     * @return double - return the gf coefficient factor for Loads.
     */
    public double getGf() {
        return gf;
    }


    /**
     * @return double - return the Neutral Axis Limit.
     */
    public double getLN() {
        return LN;
    }


    /**
     * @return double - return the flexural parameter ac
     */
    public double getAc() {
        return ac;
    }

    /**
     * @return double return the lambda
     */
    public double getLambda() {
        return lambda;
    }

    /**
     * @return ecu double - Value of the concrete strain at the Ultimate Limit State of Rupture.
     */
    public double getecu() {
        return ecu;
    }
}