package application.model;

public class Vare {
    private String navn;

    public Vare(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris(Prisliste prisliste){
        //TODO fix this
        double pris = 0;
        return pris;
    }
}
