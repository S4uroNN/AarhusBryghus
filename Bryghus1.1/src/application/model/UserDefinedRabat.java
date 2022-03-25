package application.model;

public class UserDefinedRabat implements Rabat{
    double mængde;
    public UserDefinedRabat(double mængde){
        this.mængde = mængde;
    }

    @Override
    public double beregnRabat() {
        return 0;
    }
}
