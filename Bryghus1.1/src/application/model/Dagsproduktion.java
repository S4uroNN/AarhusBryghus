package application.model;

import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dagsproduktion {
    private LocalDate dato;
    private double omsætning;

    private static Dagsproduktion dagsproduktion;

    private Storage storage;
    private final List<Salg> dagensSalg = new ArrayList<>();
    private final List<Udlejning> dagensAfsluttedeUdlejninger = new ArrayList<>();


    private Dagsproduktion() {
        this.dato = LocalDate.now();
        this.omsætning = 0;
    }

    public static Dagsproduktion getDagsproduktion() {
        if (dagsproduktion == null) {
            dagsproduktion = new Dagsproduktion();
        }
        if (!Objects.equals(dagsproduktion.getDato(), LocalDate.now())){
            Storage.getInstance().addDagsproduktion(dagsproduktion);
            dagsproduktion = new Dagsproduktion();
        }
        return dagsproduktion;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getOmsætning() {
        return omsætning;
    }

    public Salg createSalg(Prisliste prisliste) {
        Salg salg = new Salg(prisliste);
        dagensSalg.add(salg);
        return salg;
    }

    public ArrayList<Salg> getSalg() {
        return new ArrayList<Salg>(dagensSalg);
    }

    public ArrayList<Udlejning> getDagensAfsluttedeUdlejninger() {
        return new ArrayList<>(dagensAfsluttedeUdlejninger);
    }

    public void addafsluttetUdlejning(Udlejning udlejning) {
        dagensAfsluttedeUdlejninger.add(udlejning);
    }

    public void removeAfsluttetUdlejning(Udlejning udlejning) {
        dagensAfsluttedeUdlejninger.remove(udlejning);
    }

    public void updateOmsætning() {
        int omsætning = 0;
        for (Salg salg : dagensSalg) {
            omsætning += salg.samletPris();
        }
        for (Udlejning udlejning : dagensAfsluttedeUdlejninger) {
            omsætning += udlejning.samletPris();
        }
        this.omsætning = omsætning;
    }
}
