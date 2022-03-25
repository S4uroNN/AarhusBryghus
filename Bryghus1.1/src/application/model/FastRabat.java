package application.model;


public class FastRabat implements Rabat{
    private double fastRabat;

    public FastRabat(){
        this.fastRabat = 25;

    }
    @Override
    public double beregnRabat() {
        return fastRabat;
    }
}
