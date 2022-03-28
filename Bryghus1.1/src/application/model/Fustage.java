package application.model;

public class Fustage extends Drikkevare{
    private int pant;
    public Fustage(String navn, String størelse, String procenter) {
        super(navn, størelse, procenter);
        pant = 200;
    }

    public int getPant() {
        return pant;
    }
}
