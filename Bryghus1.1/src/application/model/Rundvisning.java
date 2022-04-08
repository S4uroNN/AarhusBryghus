package application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Rundvisning extends Vare implements Serializable {
    private LocalDate dato;
    private LocalTime tid;

    public Rundvisning(String navn, LocalDate dato, LocalTime tid) {
        super(navn);
        this.dato = dato;
        this.tid = tid;
    }
}
