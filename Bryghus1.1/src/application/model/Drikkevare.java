package application.model;

public class Drikkevare extends Vare {
    private String størelse;
    private String procenter;

    public Drikkevare (String navn, String størelse, String procenter){
        super(navn);
        this.størelse = størelse;
        this.procenter = procenter;

    }

    public String getProcenter() {
        return procenter;
    }

    public String getStørelse() {
        return størelse;
    }
}
