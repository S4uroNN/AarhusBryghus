package application.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dagsproduktion {
    private LocalDate dato;
    private double omsætning;

    private static Dagsproduktion dagsproduktion;


    private final List<Salg> dagensSalg = new ArrayList<>();
    private final List<Udlejning> dagensAfsluttedeUdlejninger = new ArrayList<>();


    private Dagsproduktion() {
        this.dato = LocalDate.now();
        this.omsætning = 0;
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

    public void addafsluttetUdlejning(Udlejning udlejning){
        dagensAfsluttedeUdlejninger.add(udlejning);
    }

    public void removeAfsluttetUdlejning(Udlejning udlejning){
        dagensAfsluttedeUdlejninger.remove(udlejning);
    }

    public void updateOmsætning(){
        int omsætning = 0;
        for (Salg salg : dagensSalg){
            omsætning += salg.samletPris();
        }
        for (Udlejning udlejning : dagensAfsluttedeUdlejninger){
            omsætning += udlejning.samletPris();
        }
    }
}
