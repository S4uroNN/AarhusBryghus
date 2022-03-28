package application.model;

import java.util.ArrayList;
import java.util.List;

public class OrdreLinje {
    private int id;
    private int antal;

    private Vare vare;

    public OrdreLinje(int id, int antal, Vare vare){
        this.id = id;
        this.antal = antal;
        this.vare = vare;
    }

    public int getAntal() {
        return antal;
    }
    public int getId() {
        return id;
    }
    public Vare getVare() {
        return vare;
    }
}
