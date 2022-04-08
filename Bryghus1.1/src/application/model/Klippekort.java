package application.model;

import java.io.Serializable;

public class Klippekort extends Vare implements Serializable {

    private int klip;

    public Klippekort(String navn) {
        super(navn);
        this.klip = 4;
    }

    public int getKlip() {
        return klip;
    }

    public void setKlip(int klip) {
        this.klip = klip;
    }
}
