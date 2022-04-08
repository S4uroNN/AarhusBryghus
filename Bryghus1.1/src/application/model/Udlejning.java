package application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Udlejning implements Serializable {
    private LocalDate startDato;
    private LocalDate slutDato;
    private String kontaktPerson;
    private String telefonnr;
    private String email;
    private Prisliste prisliste;
    private Rabat rabat;
    private int id;
    private int idCount = 0;


    private Betalingsform betalingsform;
    private final Set<Ordrelinje> ordrelinjer = new HashSet<>();

    public Udlejning(LocalDate startDato, LocalDate slutDato, String kontaktPerson, String telefonnr, String email, Prisliste prisliste){
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.id = idCount;
        this.kontaktPerson = kontaktPerson;
        this.telefonnr = telefonnr;
        this.email = email;
        this.prisliste = prisliste;
        idCount++;
    }

    public Ordrelinje createOrdreLinje(int antal, Vare vare){
        Ordrelinje ordrelinje = new Ordrelinje(antal, vare);
        ordrelinjer.add(ordrelinje);
        return ordrelinje;
    }
    public void removeOrdreLinje(Ordrelinje ordrelinje){
        ordrelinjer.remove(ordrelinje);
    }

    public double samletPris(){
        double samletpris = 0;
        for(Ordrelinje ordrelinje : ordrelinjer){
           samletpris += ordrelinje.getVare().getPrisMedPant(prisliste) * ordrelinje.getAntal();
        }
        return samletpris;
    }


    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public String getKontaktPerson() {
        return kontaktPerson;
    }

    public void setKontaktPerson(String kontaktPerson) {
        this.kontaktPerson = kontaktPerson;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Betalingsform getBetalingsform() {
        return betalingsform;
    }

    public void setBetalingsform(Betalingsform betalingsform) {
        this.betalingsform = betalingsform;
    }

    public Set<Ordrelinje> getOrdrelinjer() {
        return new HashSet<>(ordrelinjer);
    }

    public int getId() {
        return id;
    }

    public Rabat getRabat() {
        return rabat;
    }

    public void setRabat(Rabat rabat) {
        this.rabat = rabat;
    }

    @Override
    public String toString() {
        return kontaktPerson + " " + samletPris() + " Kroner";
    }
}
