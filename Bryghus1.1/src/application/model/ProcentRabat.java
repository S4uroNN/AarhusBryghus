package application.model;

import java.io.Serializable;

public class ProcentRabat implements Rabat, Serializable {
    double procent;

    public ProcentRabat(double procent){
        this.procent = procent;

    }

    @Override
    public double beregnRabat(double pris) {
        return pris * (procent / 100.0);
    }
}
