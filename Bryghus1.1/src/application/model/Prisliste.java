package application.model;

import java.util.HashMap;
import java.util.Map;

public class Prisliste {
    private String navn;
    private Map<Vare, Double> priser  = new HashMap<>();

    public Prisliste(String navn){
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVarePris(Vare vare){
        return priser.get(vare);
    }

    public void addVare(Vare vare, double pris){
        priser.put(vare, pris);
    }

    public Map<Vare, Double> getPriser() {
        return priser;
    }
}