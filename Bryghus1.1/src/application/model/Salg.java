package application.model;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Salg {
    private int id = 0;
    private Betalingsform betalingsform;

    //Måske tilføje betalingsform?
    private final Set<OrdreLinje> ordrer = new HashSet<>();

    public Salg(){
        this.id = id;
        id++;
    }

    public OrdreLinje createOrdreLinje(int id, int antal, Vare vare){
        OrdreLinje ordreLinje = new OrdreLinje(id, antal, vare);
        ordrer.add(ordreLinje);
        return ordreLinje;
    }

     public void setBetalingsform(Betalingsform betalingsform){
        this.betalingsform = betalingsform;
     }

    public Set<OrdreLinje> getOrdrer() {
        return new HashSet<>(ordrer);
    }

    public double samletPris(){
        double samletpris = 0;
        for(OrdreLinje o : ordrer){
            o.getVare().getPris(null);
        }
        return samletpris;
    }
}

