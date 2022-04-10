package application.controller;

import application.model.*;
import storage.Storage;

import javax.xml.xpath.XPathVariableResolver;
import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;

public class VareController {
    private Storage storage;
    private static VareController vareController;


    private VareController() {
        storage = Storage.getInstance();
    }

    public static VareController getController() {
        if (vareController == null) {
            vareController = new VareController();
        }
        return vareController;
    }

    public static VareController getTestController() {
        return new VareController();
    }


    public Vare createVare(String navn) {
        Vare vare = new Vare(navn);
        storage.addVare(vare);
        return vare;
    }

    public VareGruppe createVareGruppe(String navn, int pant) {
        VareGruppe vareGruppe = new VareGruppe(navn, pant);
        storage.addVareGruppe(vareGruppe);
        return vareGruppe;
    }

    public Prisliste createPrisliste(String navn) {
        Prisliste prisliste = new Prisliste(navn);
        storage.addPrisliste(prisliste);
        return prisliste;
    }

    public void removeVareFromGruppe(Vare vare, VareGruppe vareGruppe) {
        vareGruppe.removeVare(vare);
    }

    public void addVareToVareGruppe(Vare vare, VareGruppe vareGruppe) {
        vareGruppe.addVare(vare);
    }

    public void addVareToPrisliste(Prisliste prisliste, Vare vare, double pris, int klip) {
        prisliste.addVare(vare, pris, klip);
    }

    public void addVareGruppeToPrisliste(Prisliste prisliste, VareGruppe vareGruppe, double pris, int klip) {
        for (Vare vare : vareGruppe.getVarer()) {
            prisliste.addVare(vare, pris, klip);
        }
    }

    public void fjernVarefromPrisliste(Prisliste prisliste, Vare vare) {
        prisliste.removeVare(vare);
    }

    public void updatePris(Prisliste prisliste, Vare vare, double pris, int klip) {
        prisliste.setPris(vare, pris, klip);
    }

    public void updateVareGruppe(VareGruppe vareGruppe, String navn, int pant) {
        vareGruppe.setNavn(navn);
        vareGruppe.setPant(pant);
    }

    public void updateVare(Vare vare, String navn) {
        vare.setNavn(navn);
    }

    public void updatePrisliste(Prisliste prisliste, String navn) {
        prisliste.setNavn(navn);
    }

    public void deleteVare(Vare vare) {
        storage.removeVare(vare);
    }

    public void deleteVareGruppe(VareGruppe vareGruppe) {
        storage.removeVareGruppe(vareGruppe);
    }

    public void deletePrisliste(Prisliste prisliste) {
        storage.removePrisliste(prisliste);
    }

    public HashSet<Vare> getVarer() {
        return storage.getVarer();
    }

    public HashSet<VareGruppe> getVareGrupper() {
        return storage.getVaregrupper();
    }

    public HashSet<Prisliste> getPrislister() {
        return storage.getPrislister();
    }

    public void saveStorage() {
        try (FileOutputStream fileOut = new FileOutputStream("storage.ser")) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(storage);
                System.out.println("Storage saved in file storage.ser.");
            }
        } catch (IOException ex) {
            System.out.println("Error saving storage object.");
            throw new RuntimeException(ex);
        }
    }

}
