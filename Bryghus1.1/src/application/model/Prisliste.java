package application.model;

import java.util.HashMap;
import java.util.Map;

public class Prisliste {
    private Map<Vare, Double> priser  = new HashMap<>();

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