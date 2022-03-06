package standards;

public class Code {

    // Compressive strength of concrete
    double fck;

    // Neutral Axis limit
    double LN;

    // Defined strain of the concrete compression at the rupture
    double ecu;

    // Safety factor coefficients
    double gc; // Concrete S.F.
    double gs; // Steel S.F.
    double gf; // Force S.F.

    // Define some methods that are use in the Resistance.java Class
    public void setFck(double fck){};
    public void setCoefficients(double gc, double gs, double gf){};
    public double getFck(){return 0;};
    public double getGc() {return 0;};
    public double getGs() {return 0;};
    public double getGf() {return 0;};
    public double getLN() {return 0;};
    public double getecu() {return 0;};
    
}
