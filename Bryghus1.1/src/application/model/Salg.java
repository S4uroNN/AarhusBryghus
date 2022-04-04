package application.model;

import java.util.HashSet;
import java.util.Set;

public class Salg {
    private int id;
    private Betalingsform betalingsform;
    private int ordrelinjenr;
    private Prisliste prisliste;
    private int idCount = 0;

    private final Set<Ordrelinje> ordrelinjer = new HashSet<>();

    public Salg(Prisliste prisliste) {
        this.prisliste = prisliste;
        this.id = idCount;
        idCount++;
    }

    public Ordrelinje createOrdreLinje(int antal, Vare vare) {
        Ordrelinje ordrelinje = new Ordrelinje(antal, vare);
        ordrelinjer.add(ordrelinje);
        return ordrelinje;
    }

    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    public Set<Ordrelinje> getOrdrer() {
        return new HashSet<>(ordrelinjer);
    }

    public double samletPris() {
        double samletpris = 0;
        for (Ordrelinje ordreLinje : ordrelinjer) {
            samletpris = ordreLinje.getVare().getPris(prisliste) * ordreLinje.getAntal();
        }
        return samletpris;
    }

    public int samletPrisKlip() {
        int antalklip = 0;
        for (Ordrelinje ordreLinje : ordrelinjer) {
            antalklip = ordreLinje.getVare().getKlipPris(prisliste) * ordreLinje.getAntal();
        }
        return antalklip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public int getOrdrelinjenr() {
        return ordrelinjenr;
    }

    public void setOrdrelinjenr(int ordrelinjenr) {
        this.ordrelinjenr = ordrelinjenr;
    }

    public Set<Ordrelinje> getOrdrelinjer() {
        return new HashSet<>(ordrelinjer);
    }

    public void removeOrdrelinje(Ordrelinje ordrelinje) {
        ordrelinjer.remove(ordrelinje);
    }
}

