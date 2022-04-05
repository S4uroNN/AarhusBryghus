package application.model;


public class KontantRabat implements Rabat{
    private double beloeb;

    public KontantRabat(double beloeb){
        this.beloeb = beloeb;

    }
    @Override
    public double beregnRabat(double pris) {
        return beloeb;
    }
}
