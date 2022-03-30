package application.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dagsproduktion {
    private LocalDate dato;
    private double omsætning;

    private final List<Salg> dagensSalg = new ArrayList<>();


    public Dagsproduktion() {
        this.dato = LocalDate.now();
        this.omsætning = 0;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double getOmsætning() {
        return omsætning;
    }

    public void setOmsætning(double omsætning) {
        this.omsætning = omsætning;
    }

    public Salg createSalg() {
        Salg salg = new Salg();
        dagensSalg.add(salg);
        return salg;
    }

    public ArrayList<Salg> getSalg() {
        return new ArrayList<Salg>(dagensSalg);
    }

}
