package application.model;

import java.util.ArrayList;
import java.util.List;

public class OrdreLinje {
    private int linjenummer;
    private int antal;

    private Vare vare;

    public OrdreLinje(int linjenummer, int antal, Vare vare){
        this.linjenummer = linjenummer;
        this.antal = antal;
        this.vare = vare;
    }

    public int getAntal() {
        return antal;
    }
    public int getId() {
        return linjenummer;
    }
    public Vare getVare() {
        return vare;
    }
}
