package application.controller;

import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import storage.Storage;

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

    public void addVareToPrisliste(Prisliste prisliste, Vare vare, double pris) {
        prisliste.addVare(vare, pris);
    }

    public void addVareGruppeToPrisliste(Prisliste prisliste, VareGruppe vareGruppe, double pris) {
        for (Vare vare : vareGruppe.getVarer()) {
            prisliste.addVare(vare, pris);
        }
    }

    public void fjernVarefromPrisliste(Prisliste prisliste, Vare vare) {
        prisliste.removeVare(vare);
    }

    public void updatePris(Prisliste prisliste, Vare vare, double pris) {
        prisliste.setPris(vare, pris);
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

    //------------------------------------------------------------------------------------------------

    public void initStorage() {
        VareGruppe fustage = vareController.createVareGruppe("Fustage", 200);
        VareGruppe fadøl = vareController.createVareGruppe("Fadøl, 40cl", 0);
        VareGruppe flaske = vareController.createVareGruppe("Flaske", 0);
        VareGruppe spiritus = vareController.createVareGruppe("Spiritus", 0);
        VareGruppe kulsyre = vareController.createVareGruppe("Kulsyre", 1000);
        VareGruppe malt = vareController.createVareGruppe("Malt", 0);
        VareGruppe beklædning = vareController.createVareGruppe("Beklædning", 0);
        VareGruppe anlæg = vareController.createVareGruppe("Anlæg", 0);
        VareGruppe glas = vareController.createVareGruppe("Glas", 0);
        VareGruppe sampakninger = vareController.createVareGruppe("Sampakninger", 0);

        Vare klosterbryg = vareController.createVare("Klosterbryg");
        Vare sweetGeorgiaBrown = vareController.createVare("Sweet Georgia Brown");
        Vare extraPilsner = vareController.createVare("Extra Pilsner");
        Vare forårsbryg = vareController.createVare("Forårsbryg");
        Vare whisky4550cl = vareController.createVare("Whiskey 45% 50 cl rør");
        Vare sixkgKulsyre = vareController.createVare("6kg");
        Vare twentyfivekgsæk = vareController.createVare("25 kg sæk");
        Vare polo = vareController.createVare("Polo");
        Vare barmedflerehaner = vareController.createVare("Bar med flere haner");
        Vare glasUansetStørrelse = vareController.createVare("Glas");
        Vare gaveæske4øl = vareController.createVare("Gaveæske 4 øl");
        Vare rundvisning10prs = vareController.createVare("Rundvisning 10 personer");

        vareController.addVareToVareGruppe(klosterbryg, flaske);
        vareController.addVareToVareGruppe(klosterbryg, fustage);
        vareController.addVareToVareGruppe(extraPilsner, fadøl);
        vareController.addVareToVareGruppe(sweetGeorgiaBrown, flaske);
        vareController.addVareToVareGruppe(forårsbryg, flaske);
        vareController.addVareToVareGruppe(forårsbryg, fadøl);
        vareController.addVareToVareGruppe(forårsbryg, fustage);
        vareController.addVareToVareGruppe(whisky4550cl, spiritus);
        vareController.addVareToVareGruppe(sixkgKulsyre, kulsyre);
        vareController.addVareToVareGruppe(twentyfivekgsæk, malt);
        vareController.addVareToVareGruppe(polo, beklædning);
        vareController.addVareToVareGruppe(barmedflerehaner, anlæg);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);
        vareController.addVareToVareGruppe(gaveæske4øl, sampakninger);


    }


}
