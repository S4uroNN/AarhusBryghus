package application.model;

public class ProcentRabat implements Rabat{
    double procent;

    public ProcentRabat(double procent){
        this.procent = procent;

    }

    @Override
    public double beregnRabat() {
        return 0;
    }
}
