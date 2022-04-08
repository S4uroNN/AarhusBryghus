package application.model;


import java.io.Serializable;

public class KontantRabat implements Rabat, Serializable {
    private double beloeb;

    public KontantRabat(double beloeb){
        this.beloeb = beloeb;

    }


    @Override
    public double beregnRabat(double pris) {
        return beloeb;
    }
}
