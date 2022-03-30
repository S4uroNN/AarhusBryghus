package application.controller;

import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import storage.Storage;

import java.util.HashSet;

public class Controller {
    private Storage storage;
    private static Controller controller;

    private Controller() {
        storage = Storage.getInstance();
    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
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
        VareGruppe fustage = controller.createVareGruppe("Fustage", 200);
        VareGruppe fadøl = controller.createVareGruppe("Fadøl, 40cl", 0);
        VareGruppe flaske = controller.createVareGruppe("Flaske", 0);
        VareGruppe spiritus = controller.createVareGruppe("Spiritus", 0);
        VareGruppe kulsyre = controller.createVareGruppe("Kulsyre", 1000);
        VareGruppe malt = controller.createVareGruppe("Malt", 0);
        VareGruppe beklædning = controller.createVareGruppe("Beklædning", 0);
        VareGruppe anlæg = controller.createVareGruppe("Anlæg", 0);
        VareGruppe glas = controller.createVareGruppe("Glas", 0);
        VareGruppe sampakninger = controller.createVareGruppe("Sampakninger", 0);

        Vare klosterbryg = controller.createVare("Klosterbryg");
        Vare sweetGeorgiaBrown = controller.createVare("Sweet Georgia Brown");
        Vare extraPilsner = controller.createVare("Extra Pilsner");
        Vare forårsbryg = controller.createVare("Forårsbryg");
        Vare whisky4550cl = controller.createVare("Whiskey 45% 50 cl rør");
        Vare sixkgKulsyre = controller.createVare("6kg");
        Vare twentyfivekgsæk = controller.createVare("25 kg sæk");
        Vare polo = controller.createVare("Polo");
        Vare barmedflerehaner = controller.createVare("Bar med flere haner");
        Vare glasUansetStørrelse = controller.createVare("Glas");
        Vare gaveæske4øl = controller.createVare("Gaveæske 4 øl");
        Vare rundvisning10prs = controller.createVare("Rundvisning 10 personer");

        controller.addVareToVareGruppe(klosterbryg, flaske);
        controller.addVareToVareGruppe(klosterbryg, fustage);
        controller.addVareToVareGruppe(extraPilsner, fadøl);
        controller.addVareToVareGruppe(sweetGeorgiaBrown, flaske);
        controller.addVareToVareGruppe(forårsbryg, flaske);
        controller.addVareToVareGruppe(forårsbryg, fadøl);
        controller.addVareToVareGruppe(forårsbryg, fustage);
        controller.addVareToVareGruppe(whisky4550cl, spiritus);
        controller.addVareToVareGruppe(sixkgKulsyre, kulsyre);
        controller.addVareToVareGruppe(twentyfivekgsæk, malt);
        controller.addVareToVareGruppe(polo, beklædning);
        controller.addVareToVareGruppe(barmedflerehaner, anlæg);
        controller.addVareToVareGruppe(glasUansetStørrelse, glas);
        controller.addVareToVareGruppe(gaveæske4øl, sampakninger);


    }


}
