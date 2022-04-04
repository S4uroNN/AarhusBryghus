package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prisliste {
    private String navn;
    private Map<Vare, Double> priser  = new HashMap<>();
    private Map<Vare, Integer> klippekortPriser = new HashMap<>();

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
        return priser.get(vare) + vare.getVareGruppe().getPant();
    }

    public int getKlipPris(Vare vare){
        return klippekortPriser.get(vare);
    }

    public void addVare(Vare vare, double pris, int klip){
        priser.put(vare, pris);
        klippekortPriser.put(vare,klip);
    }

    public void removeVare(Vare vare){
        priser.remove(vare);
        klippekortPriser.remove(vare);
    }

    public void setPris(Vare vare, double pris, int klip){
        priser.replace(vare,pris);
        klippekortPriser.replace(vare,klip);
    }

    public Map<Vare, Double> getPriser() {
        return priser;
    }

    public Map<Vare,Integer> getKlipPriser(){
        return klippekortPriser;
    }

    public ArrayList<Vare> getTilføjedeVarer(){
        ArrayList<Vare> tilføjedeVarer = new ArrayList();
        for (Vare vare : priser.keySet()){
            tilføjedeVarer.add(vare);
        }
        return  tilføjedeVarer;
    }

    public String toString(){
        return navn;
    }
}