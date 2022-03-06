
package standards;

/**
 * Class that contains parameters of the Brazilian Code - NBR 6118:2014.
 * Handles all parameters and safety coefficients related to Structural Concrete Calculation.
 */
public class BrazilianCode extends Code{

    // Compressive strength of concrete
    private double fck;

    // Neutral Axis limit
    private double LN;

    // Flexural parameters
    private double ec2;
    private double ecu;
    private double n;

    // Tension parameters
    private double fctm;
    private double fctkinf;
    private double fctksup;

    // Constructor that defines its Safety Factor coeffeficients (SuperClass Code)
    public BrazilianCode(){
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
        if (this.fck <= 50){
            this.ec2 = 2.0/1000;
            this.ecu = 3.5/1000;
            this.n = 2;
            this.LN = 0.45;
            this.fctm = 0.3*Math.pow(fck, (2.0/3));
        } else {
            this.ec2 = 2.0/1000 + (0.085/1000)*(Math.pow((fck-50), 0.53));
            this.ecu = 2.6/1000 + (35.0/1000)*(Math.pow(((90.0-fck)/100), 4));
            this.n = 1.4 + 23.4*(Math.pow(((90.0-fck)/100), 4));
            this.LN = 0.35;
            this.fctm = 2.12*Math.log(1+0.11*fck);
        }
        this.fctkinf = 0.7*fctm;
        this.fctksup = 1.3*fctm;
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
        "Concrete Parameters according to Brazilian NBR 6118:2014 code: \n" + 
        "  fck     =\t"     + Math.round(this.fck * 1000.0)     / 1000.0 + "MPa,\n" +
        "  LN      =\t"      + Math.round(this.LN*1000)          / 1000.0 + "*d, \n" +     
        "  ec2     =\t"     + Math.round(this.ec2 * 10000.0)    / 10000.0 + "%%,\n" +
        "  ecu     =\t"     + Math.round(this.ecu * 10000.0)    / 10000.0 + "%%,\n" +
        "  n       =\t"       + Math.round(this.n * 1000.0)       / 1000.0 + ",\n"    +
        "  fctm    =\t"    + Math.round(this.fctm * 1000.0)    / 1000.0 + "MPa,\n" +
        "  fctkinf =\t" + Math.round(this.fctkinf * 1000.0) / 1000.0 + "MPa,\n" +
        "  fctksup =\t" + Math.round(this.fctksup * 1000.0) / 1000.0 + "MPa. \n");
    }



    /**
     * @return double - return the compressive strength of concrete - fck
     */
    public double getFck() {
        return fck;
    }


    /**
     * @return double - return the concrete strain at the rupture by compression.
     */
    public double getec2() {
        return ec2;
    }


    /**
     * @return double - return the concrete strain at the Ultimate Limit State of Rupture.
     */
    public double getecu() {
        return ecu;
    }


    /**
     * @return double - return the Flexural parameter n.
     */
    public double getN() {
        return n;
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
     * @return double return the fctm.
     */
    public double getFctm() {
        return fctm;
    }


    /**
     * @return double return the fctkinf.
     */
    public double getFctkinf() {
        return fctkinf;
    }


    /**
     * @return double return the fctksup.
     */
    public double getFctksup() {
        return fctksup;
    }
}