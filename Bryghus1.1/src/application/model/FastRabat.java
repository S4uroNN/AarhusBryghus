package application.model;


public class FastRabat implements Rabat{
    private double fastRabat;

    public FastRabat(double fastRabat){
        this.fastRabat = fastRabat;

    }
    @Override
    public double beregnRabat() {
        return 0;
    }
}
