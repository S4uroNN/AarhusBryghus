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

    public double getPrisMedPant(Prisliste prisliste){
        return prisliste.getVarePris(this) + vareGruppe.getPant();
    }

    public double getPrisUdenPant(Prisliste prisliste) {return prisliste.getVarePris(this);}

    public int getKlipPris(Prisliste prisliste){
        return prisliste.getKlipPris(this);
    }

}
