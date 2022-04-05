package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.HashSet;

public class VareController {
    private Storage storage;
    private static VareController vareController;
    private SalgController salgController = SalgController.getSalgController();
    private Dagsproduktion dagsproduktion = SalgController.getDagsproduktion();

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

        Prisliste butik = vareController.createPrisliste("Butik");
        Prisliste fredagsweehoo = vareController.createPrisliste("Fredags Cafe");

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

        vareController.addVareGruppeToPrisliste(butik,fadøl,20,2);
        vareController.addVareGruppeToPrisliste(butik,flaske,17, 2);
        vareController.addVareGruppeToPrisliste(butik,kulsyre,1000,20);

        vareController.addVareGruppeToPrisliste(fredagsweehoo,fadøl,40, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,flaske,70, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,spiritus, 1000, 40);

        Udlejning udlejning = salgController.createUdlejning(LocalDate.now(),LocalDate.now(),"MAthias","51482610","alin_con",butik);
        udlejning.createOrdreLinje(5,extraPilsner);

        Udlejning udlejning1 = salgController.createUdlejning(LocalDate.now(),LocalDate.now(),"MAthias","51482610","alin_con",butik);
        udlejning1.createOrdreLinje(5,extraPilsner);

        salgController.afslutUdlejning(udlejning,dagsproduktion,Betalingsform.DANKORT);
        salgController.updateOmsætning(dagsproduktion);


    }


}
