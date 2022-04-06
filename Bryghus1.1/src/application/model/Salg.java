package application.model;

import java.util.HashSet;
import java.util.Set;

public class Salg {
    private int id;
    private Betalingsform betalingsform;
    private Prisliste prisliste;
    private int idCount = 0;
    private Rabat rabat;

    private final Set<Ordrelinje> ordrelinjer = new HashSet<>();


    /**
     *
     * @param prisliste
     * pre: prisliste != null
     */
    public Salg(Prisliste prisliste) {
        this.prisliste = prisliste;
        this.id = idCount;
        idCount++;
    }

    /**
     *
     * @param antal
     * antal > 0
     * antal == Int
     * @param vare
     * vare != null
     * @return
     */
    public Ordrelinje createOrdreLinje(int antal, Vare vare) {
        Ordrelinje ordrelinje = new Ordrelinje(antal, vare);
        ordrelinjer.add(ordrelinje);
        return ordrelinje;
    }

    /**
     *
     * @param betalingsform
     * betalingsform != null
     */
    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    public Set<Ordrelinje> getOrdrer() {
        return new HashSet<>(ordrelinjer);
    }

    /**
     *
     * @return
     */
    public double samletPris() {
        double samletpris = 0;
        for(Ordrelinje ordreLinje : ordrelinjer){
            samletpris += ordreLinje.getVare().getPrisMedPant(prisliste) * ordreLinje.getAntal();
        }
        if (rabat != null){
            samletpris = samletpris - rabat.beregnRabat(samletpris);
        }
        return samletpris;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param id
     * id != null
     */
    public void setId(int id) {
        this.id = id;
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public Set<Ordrelinje> getOrdrelinjer() {
        return new HashSet<>(ordrelinjer);
    }

    /**
     *
     * @param ordrelinje
     * ordrelinje != null
     */
    public void removeOrdrelinje(Ordrelinje ordrelinje) {
        ordrelinjer.remove(ordrelinje);
    }

    public Rabat getRabat() {
        return rabat;
    }

    public void setRabat(Rabat rabat) {
        this.rabat = rabat;
    }
}

