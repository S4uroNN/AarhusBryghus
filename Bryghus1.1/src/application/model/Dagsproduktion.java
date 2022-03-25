package application.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dagsproduktion {
    private LocalDate dato;
    private LocalTime tid;
    private double omsætning;

    private final List<Salg> salgs = new ArrayList<>();

    public Dagsproduktion(LocalDate dato, LocalTime tid){
        this.omsætning = 0;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public LocalTime getTid() {
        return tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    public double getOmsætning() {
        return omsætning;
    }

    public void setOmsætning(double omsætning) {
        this.omsætning = omsætning;
    }

    public Salg createSalg(int id){
        return null;
    }
    public ArrayList<Salg> getSalg(){
        return new ArrayList<Salg>(salgs);
    }
}
