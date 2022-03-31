package application.model;

import java.util.HashSet;
import java.util.Set;

public class Salg {
    private int id = 0;
    private Betalingsform betalingsform;
    private int ordrelinjenr;

    //Måske tilføje betalingsform?
    private final Set<OrdreLinje> ordrelinjer = new HashSet<>();

    public Salg(){
        this.id = id;
        id++;
        this.ordrelinjenr = 0;
    }

    public OrdreLinje createOrdreLinje(int ordrelinjenr, int antal, Vare vare){
        OrdreLinje ordreLinje = new OrdreLinje(id, antal, vare);
        ordrelinjer.add(ordreLinje);
        return ordreLinje;
    }

     public void setBetalingsform(Betalingsform betalingsform){
        this.betalingsform = betalingsform;
     }

    public Set<OrdreLinje> getOrdrer() {
        return new HashSet<>(ordrelinjer);
    }

    public double samletPris(Prisliste prisliste){
        double samletpris = 0;
        for(OrdreLinje ordreLinje : ordrelinjer){
            samletpris = ordreLinje.getVare().getPris(prisliste) * ordreLinje.getAntal();
        }
        return samletpris;
    }
}

