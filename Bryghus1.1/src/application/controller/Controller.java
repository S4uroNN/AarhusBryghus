package application.controller;

import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import storage.Storage;

import java.util.HashSet;

public class Controller {
    private Storage storage;
    private Controller controller;

    private Controller() {
        storage = Storage.getInstance();
    }

    public Controller getController(){
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }


    public Vare createVare(String navn){
        Vare vare = new Vare(navn);
        storage.addVare(vare);
        return vare;
    }
    public VareGruppe createVareGruppe(String navn){
        VareGruppe vareGruppe = new VareGruppe(navn);
        storage.addVareGruppe(vareGruppe);
        return vareGruppe;
    }
    public Prisliste createPrisliste(String navn){
        Prisliste prisliste = new Prisliste(navn);
        storage.addPrisliste(prisliste);
        return prisliste;
    }

    public void addVareToVareGruppe(Vare vare, VareGruppe vareGruppe){vareGruppe.addVare(vare);}

    public void updateVareGruppe(VareGruppe vareGruppe, String navn) {
        vareGruppe.setNavn(navn);
    }
    public void updateVare(Vare vare, String navn) {
        vare.setNavn(navn);
    }
    public void updatePrisliste(Prisliste prisliste, String navn){
        prisliste.setNavn(navn);
    }
    public void deleteVare(Vare vare){
        storage.removeVare(vare);
    }
    public void deleteVareGruppe(VareGruppe vareGruppe){
        storage.removeVareGruppe(vareGruppe);
    }
    public void deletePrisliste(Prisliste prisliste){
        storage.removePrisliste(prisliste);
    }

    public HashSet<Vare> getVarer(){
        return storage.getVarer();
    }

    public HashSet<VareGruppe> getVareGrupper(){
        return storage.getVaregrupper();
    }

    public HashSet<Prisliste> getPrisliste(){
        return storage.getPrislister();
    }










}