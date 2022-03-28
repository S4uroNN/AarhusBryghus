package application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Udlejning {
    private LocalDate startDato;
    private LocalDate slutDato;
    private String kontaktPerson;
    private String telefonnr;
    private String email;
    private int id = 0;


    private Betalingsform betalingsform;
    private final Set<OrdreLinje> ordrer = new HashSet<>();

    public Udlejning(LocalDate startDato, LocalDate slutDato, String kontaktPerson, String telefonnr, String email){
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.id = id;
        this.kontaktPerson = kontaktPerson;
        this.telefonnr = telefonnr;
        this.email = email;

        id++;
    }

    public double samletPris(){
        double samletpris = 0;
        for(OrdreLinje o : ordrer){
           o.getVare().getPris(null);
        }
        return samletpris;
    }
}
