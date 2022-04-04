package application.model;

public class Klippekort extends Vare{

    private int klip;

    public Klippekort(String navn) {
        super(navn);
        this.klip = 10;
    }

    public int getKlip() {
        return klip;
    }

    public void setKlip(int klip) {
        this.klip = klip;
    }
}
