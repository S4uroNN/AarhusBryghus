package application.model;


public class Vare {
    private String navn;
    private VareGruppe vareGruppe;

    public Vare(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }

    public VareGruppe getVareGruppe() {
        return vareGruppe;
    }

    public void setVareGruppe(VareGruppe vareGruppe) {
        this.vareGruppe = vareGruppe;
    }

    /**
     *
     * @param prisliste
     * @return returnerer pris med pant hvis hvis varen er tilknyttet en varegruppe
     */
    public double getPrisMedPant(Prisliste prisliste){
        double pris = 0;
        if (vareGruppe != null) {
            pris = prisliste.getVarePris(this) + vareGruppe.getPant();
        } else {
            pris = prisliste.getVarePris(this);
        }
        return pris;
    }

    /**
     *
     * @param prisliste
     * @return returner prisen for varen uden pant
     */
    public double getPrisUdenPant(Prisliste prisliste) {return prisliste.getVarePris(this);}

    public int getKlipPris(Prisliste prisliste){
        return prisliste.getKlipPris(this);
    }

}
