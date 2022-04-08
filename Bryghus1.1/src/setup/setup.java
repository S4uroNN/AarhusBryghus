package setup;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.*;

import java.time.LocalDate;

public class setup {

    public static void main(String[] args) {
        initStorage();
    }

    /**
     * Initializes the storage with some objects.
     */
    private static void initStorage() {
        VareController vareController = VareController.getController();
        SalgController salgController = SalgController.getSalgController();

        //--VareGruppe-------
        VareGruppe flaske = vareController.createVareGruppe("Flaske", 0);
        VareGruppe fadøl = vareController.createVareGruppe("Fadøl, 40cl", 0);
        VareGruppe spiritus = vareController.createVareGruppe("Spiritus", 0);
        VareGruppe fustage = vareController.createVareGruppe("Fustage", 200);
        VareGruppe kulsyre = vareController.createVareGruppe("Kulsyre", 1000);
        VareGruppe malt = vareController.createVareGruppe("Malt", 0);
        VareGruppe beklædning = vareController.createVareGruppe("Beklædning", 0);
        VareGruppe anlæg = vareController.createVareGruppe("Anlæg", 500);
        VareGruppe glas = vareController.createVareGruppe("Glas", 0);
        VareGruppe sampakninger = vareController.createVareGruppe("Sampakninger", 0);

        //--Vare--------

        ////Flaske
        Vare klosterbrygFlaske = vareController.createVare("Klosterbryg Flaske");
        Vare sweetGeorgiaBrownFlaske = vareController.createVare("Sweet Georgia Brown Flaske");
        Vare ekstraPilsnerFlaske = vareController.createVare("Ekstra Pilsner Flaske");
        Vare celebrationFlaske = vareController.createVare("Celebration Flaske");
        Vare blondieFlaske = vareController.createVare("Blondie Flaske");
        Vare forårsbrygFlaske = vareController.createVare("Forårsbryg Flaske");
        Vare indianPaleAleFlaske = vareController.createVare("Indian Pale Ale Flaske");
        Vare julebrygFlaske = vareController.createVare("Julebryg Flaske");
        Vare juletøndenFlaske = vareController.createVare("Juletønden Flaske");
        Vare oldStrongAleFlaske = vareController.createVare("Old Strong Ale Flaske");
        Vare fregattenJyllandFlaske = vareController.createVare("Fregatten Jylland Flaske");
        Vare imperialStoutFlaske = vareController.createVare("Imperial Stout Flaske");
        Vare tributeFlaske = vareController.createVare("Tribute Flaske");
        Vare blackMonsterFlaske = vareController.createVare("Black Monster Flaske");

        ////Fadøl
        Vare klosterbrygFadøl = vareController.createVare("Klosterbryg Fadøl");
        Vare jazzClasicFadøl = vareController.createVare("Jazz Classic Fadøl");
        Vare extraPilsnerFadøl = vareController.createVare("Extra Pilsner Fadøl");
        Vare celebrationFadøl = vareController.createVare("Celebration Fadøl");
        Vare blondieFadøl = vareController.createVare("Blondie Fadøl");
        Vare forårsbrygFadøl = vareController.createVare("Forårsbryg Fadøl");
        Vare indianPaleAleFadøl = vareController.createVare("Indian Pale Ale Fadøl");
        Vare julebrygFadøl = vareController.createVare("Julebryg Fadøl");
        Vare imperialStoutFadøl = vareController.createVare("Imperial Stout Fadøl");
        Vare specielFadøl = vareController.createVare("Special Fadøl");

        Vare æblebrus = vareController.createVare("Æblebrus");
        Vare chips = vareController.createVare("Chips");
        Vare peanuts = vareController.createVare("PØeanuts");
        Vare cola = vareController.createVare("Cola");
        Vare nikoline = vareController.createVare("Nikoline");
        Vare sevenup = vareController.createVare("7up");
        Vare vand = vareController.createVare("Vand");
        Vare ølpølser = vareController.createVare("Ølpølser");

        ////Spiritus
        Vare whisky4550cl = vareController.createVare("Whiskey 45% 50 cl rør");
        Vare whisky4cl = vareController.createVare("Whiskey 4 cl.");
        Vare whisky4350 = vareController.createVare("Whiskey 43% 50 cl rør");
        Vare uegesplint = vareController.createVare("u/ egesplint");
        Vare megensplint = vareController.createVare("m/ egensplint");
        Vare whiskeyplusbrikker = vareController.createVare("2 * Whiskey glas + brikker");
        Vare liquorofAarhus = vareController.createVare("Liquor of Aarhus");
        Vare lyng50cl = vareController.createVare("Lyng gin 50cl");
        Vare lyng4cl = vareController.createVare("Lyng gin 4cl");

        ////Fustage
        Vare klosterbrygFustage = vareController.createVare("Klosterbryg 20l");
        Vare klosterbrygFustage30 = vareController.createVare("Klosterbryg 30l");
        Vare jazz = vareController.createVare("Jazz Classic 25l");
        Vare jazz30 = vareController.createVare("Jazz Classic 30l");
        Vare extrapilsner = vareController.createVare("Extra Pilsner 25l");
        Vare extrapilsner30 = vareController.createVare("Extra Pilsner 30l");
        Vare celebration = vareController.createVare("Celebration 20l");
        Vare celebration30 = vareController.createVare("Celebration 30l");
        Vare blondie = vareController.createVare("Blondie 25l");
        Vare blondie30 = vareController.createVare("Blondie 30l");
        Vare forårsbrygFustage = vareController.createVare("Forårsbryg Fustage 20l");
        Vare forårsbrygFustage30 = vareController.createVare("Forårsbryg Fustage 30l");
        Vare indianale = vareController.createVare("Indian Pale Ale 20l");
        Vare indianale30 = vareController.createVare("Indian Pale Ale 30l");
        Vare julebryg = vareController.createVare("Julebryg 20l");
        Vare julebryg30 = vareController.createVare("Julebryg 30l");
        Vare imperialstout = vareController.createVare("Imperial Stout 20l");
        Vare imperialstout30 = vareController.createVare("Imperial Stout 30l");

        ////Kulsyre
        Vare sixkgKulsyre = vareController.createVare("6KG Kulsyre");
        Vare firekg = vareController.createVare("4KG Kulsyre");
        Vare tikg = vareController.createVare("10KG Kulsyre");

        ////Malt
        Vare tfKgMalt = vareController.createVare("25KG Malt");

        ////Beklædning
        Vare polo = vareController.createVare("Polo");
        Vare tshirt = vareController.createVare("T-Shirt");
        Vare cap = vareController.createVare("Cap");

        ////Anlæg
        Vare enhane = vareController.createVare("1-Hane");
        Vare tohaner = vareController.createVare("2-Haner");
        Vare barmedflerehaner = vareController.createVare("Bar med flere haner");
        Vare krus = vareController.createVare("Krus");

        ////Glas
        Vare glasUansetStørrelse = vareController.createVare("Glas");

        ////Sampakninger
        Vare gaveæske2øl2glas = vareController.createVare("Gaveækse 2øl og 2 glas");
        Vare gaveæske4øl = vareController.createVare("Gaveæske 4 øl");
        Vare trækasse6øl = vareController.createVare("Trækasse 6 øl");
        Vare gavekurv6øl2glas = vareController.createVare("Gavekurv 6 øl og 2 glas");
        Vare trækasse6øl6glas = vareController.createVare("Trækasse 6 øl og 6 glas");
        Vare træasse12øl = vareController.createVare("Trækasse 12 øl");
        Vare papkasse12øl = vareController.createVare("Papkasse 12 øl");

        //Rundvisning
        Vare rundvisning10prs = vareController.createVare("Rundvisning 10 personer");
        Vare klippekort = vareController.createVare("Klippekort");

        Prisliste butik = vareController.createPrisliste("Butik");
        Prisliste fredagsweehoo = vareController.createPrisliste("Fredags Cafe");
        Prisliste udlejningprisliste = vareController.createPrisliste("Udlejning");

        //--VareGruppeTilføjelse----Flaske--
        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(sweetGeorgiaBrownFlaske, flaske);
        vareController.addVareToVareGruppe(ekstraPilsnerFlaske, flaske);
        vareController.addVareToVareGruppe(celebrationFlaske, flaske);
        vareController.addVareToVareGruppe(blondieFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFlaske, flaske);
        vareController.addVareToVareGruppe(indianPaleAleFlaske, flaske);
        vareController.addVareToVareGruppe(julebrygFlaske, flaske);
        vareController.addVareToVareGruppe(juletøndenFlaske, flaske);
        vareController.addVareToVareGruppe(oldStrongAleFlaske, flaske);
        vareController.addVareToVareGruppe(fregattenJyllandFlaske, flaske);
        vareController.addVareToVareGruppe(imperialStoutFlaske, flaske);
        vareController.addVareToVareGruppe(tributeFlaske, flaske);
        vareController.addVareToVareGruppe(blackMonsterFlaske, flaske);

        //--VareGruppeTilføjelse----Fadøl--
        vareController.addVareToVareGruppe(klosterbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(jazzClasicFadøl, fadøl);
        vareController.addVareToVareGruppe(extraPilsnerFadøl, fadøl);
        vareController.addVareToVareGruppe(celebrationFadøl, fadøl);
        vareController.addVareToVareGruppe(blondieFadøl, fadøl);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(indianPaleAleFadøl, fadøl);
        vareController.addVareToVareGruppe(julebrygFadøl, fadøl);
        vareController.addVareToVareGruppe(imperialStoutFadøl, fadøl);
        vareController.addVareToVareGruppe(specielFadøl, fadøl);
        vareController.addVareToVareGruppe(æblebrus, fadøl);
        vareController.addVareToVareGruppe(chips, fadøl);
        vareController.addVareToVareGruppe(peanuts, fadøl);
        vareController.addVareToVareGruppe(cola, fadøl);
        vareController.addVareToVareGruppe(nikoline, fadøl);
        vareController.addVareToVareGruppe(sevenup, fadøl);
        vareController.addVareToVareGruppe(vand, fadøl);
        vareController.addVareToVareGruppe(ølpølser, fadøl);

        //--VareGruppeTilføjelse----Spiritus--
        vareController.addVareToVareGruppe(whisky4550cl, spiritus);
        vareController.addVareToVareGruppe(whisky4cl, spiritus);
        vareController.addVareToVareGruppe(whisky4350, spiritus);
        vareController.addVareToVareGruppe(uegesplint, spiritus);
        vareController.addVareToVareGruppe(megensplint, spiritus);
        vareController.addVareToVareGruppe(whiskeyplusbrikker, spiritus);
        vareController.addVareToVareGruppe(liquorofAarhus, spiritus);
        vareController.addVareToVareGruppe(lyng50cl, spiritus);
        vareController.addVareToVareGruppe(lyng4cl, spiritus);

        //--VareGruppeTilføjelse----Fustage--
        vareController.addVareToVareGruppe(klosterbrygFustage, fustage);
        vareController.addVareToVareGruppe(klosterbrygFustage30, fustage);
        vareController.addVareToVareGruppe(jazz, fustage);
        vareController.addVareToVareGruppe(jazz30, fustage);
        vareController.addVareToVareGruppe(extrapilsner, fustage);
        vareController.addVareToVareGruppe(extrapilsner30, fustage);
        vareController.addVareToVareGruppe(celebration, fustage);
        vareController.addVareToVareGruppe(celebration30, fustage);
        vareController.addVareToVareGruppe(blondie, fustage);
        vareController.addVareToVareGruppe(blondie30, fustage);
        vareController.addVareToVareGruppe(forårsbrygFustage, fustage);
        vareController.addVareToVareGruppe(forårsbrygFustage30, fustage);
        vareController.addVareToVareGruppe(indianale, fustage);
        vareController.addVareToVareGruppe(indianale30, fustage);
        vareController.addVareToVareGruppe(julebryg, fustage);
        vareController.addVareToVareGruppe(julebryg30, fustage);
        vareController.addVareToVareGruppe(imperialstout, fustage);
        vareController.addVareToVareGruppe(imperialstout30, fustage);

        //--VareGruppeTilføjelse----Kulsyre--
        vareController.addVareToVareGruppe(sixkgKulsyre, kulsyre);
        vareController.addVareToVareGruppe(firekg, kulsyre);
        vareController.addVareToVareGruppe(tikg, kulsyre);

        //--VareGruppeTilføjelse----Malt--
        vareController.addVareToVareGruppe(tfKgMalt, malt);

        //--VareGruppeTilføjelse----Beklædning--
        vareController.addVareToVareGruppe(polo, beklædning);
        vareController.addVareToVareGruppe(tshirt, beklædning);
        vareController.addVareToVareGruppe(cap, beklædning);

        //--VareGruppeTilføjelse----Anlæg--
        vareController.addVareToVareGruppe(enhane, anlæg);
        vareController.addVareToVareGruppe(tohaner, anlæg);
        vareController.addVareToVareGruppe(barmedflerehaner, anlæg);
        vareController.addVareToVareGruppe(krus, anlæg);

        //--VareGruppeTilføjelse----Glas--
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        //--VareGruppeTilføjelse----Sampakninger--
        vareController.addVareToVareGruppe(gavekurv6øl2glas, sampakninger);
        vareController.addVareToVareGruppe(gaveæske4øl, sampakninger);
        vareController.addVareToVareGruppe(trækasse6øl, sampakninger);
        vareController.addVareToVareGruppe(gavekurv6øl2glas, sampakninger);
        vareController.addVareToVareGruppe(trækasse6øl6glas, sampakninger);
        vareController.addVareToVareGruppe(træasse12øl, sampakninger);
        vareController.addVareToVareGruppe(papkasse12øl, sampakninger);

        //--PrislisteTilføjelse----Butik--
        vareController.addVareGruppeToPrisliste(butik, flaske, 36, 0);
        vareController.addVareGruppeToPrisliste(butik, fustage, 775, 0);
        vareController.addVareGruppeToPrisliste(butik, kulsyre, 1000, 0);
        vareController.addVareGruppeToPrisliste(butik, spiritus, 1000, 0);
        vareController.addVareGruppeToPrisliste(butik, malt, 300, 0);
        vareController.addVareGruppeToPrisliste(butik, beklædning, 70, 0);
        vareController.addVareGruppeToPrisliste(butik, anlæg, 500, 0);
        vareController.addVareGruppeToPrisliste(butik, glas, 15, 0);
        vareController.addVareGruppeToPrisliste(butik, sampakninger, 260, 0);
        vareController.addVareToPrisliste(butik, rundvisning10prs, 1000, 0);
        vareController.addVareToPrisliste(butik, klippekort, 130, 4);
        //flaskeændringer
        butik.setPris(blackMonsterFlaske, 60, 0);
        //spiritusændringer
        butik.setPris(whisky4350, 499, 0);
        butik.removeVare(whisky4cl);
        butik.setPris(uegesplint, 300, 0);
        butik.setPris(megensplint, 350, 0);
        butik.setPris(whiskeyplusbrikker, 80, 0);
        butik.setPris(liquorofAarhus, 175, 0);
        butik.setPris(lyng50cl, 350, 0);
        butik.removeVare(lyng4cl);
        //fustageændringer
        butik.setPris(klosterbrygFustage30, ((775 / 20.0) * 30), 0);
        butik.setPris(jazz30, ((625 / 25.0) * 30), 0);
        butik.setPris(extrapilsner, 575, 0);
        butik.setPris(extrapilsner30, ((575 / 25.1) * 30), 0);
        butik.setPris(celebration30, ((775 / 20.0) * 30), 0);
        butik.setPris(blondie, 700, 0);
        butik.setPris(blondie30, ((700 / 20.0) * 30), 0);
        butik.setPris(forårsbrygFustage30, ((775 / 20.0) * 30), 0);
        butik.setPris(indianale30, ((775 / 20.0) * 30), 0);
        butik.setPris(julebryg30, ((775 / 20.0) * 30), 0);
        butik.setPris(imperialstout30, ((775 / 20.0) * 30), 0);
        //Kulsyre
        butik.setPris(firekg, ((400 / 6.0) * 4), 0);
        butik.setPris(tikg, ((400 / 6.0) * 10), 0);
        //beklædningændringer
        butik.setPris(polo, 100, 0);
        butik.setPris(cap, 30, 0);
        //anlægændringer
        butik.setPris(enhane, 250, 0);
        butik.setPris(tohaner, 400, 0);
        butik.setPris(krus, 60, 0);
        //sampakningerændringer
        butik.setPris(gaveæske2øl2glas, 110, 0);
        butik.setPris(gaveæske4øl, 140, 0);
        butik.setPris(trækasse6øl6glas, 350, 0);
        butik.setPris(træasse12øl, 410, 0);
        butik.setPris(papkasse12øl, 370, 0);

        //--PrislisteTilføjelse----FredagsCafé--
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 38, 1);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, spiritus, 70, 0);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, kulsyre, 400, 0);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, beklædning, 100, 0);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, sampakninger, 260, 0);

        //flaskeændringer
        fredagsweehoo.setPris(blackMonsterFlaske, 100, 3);
        //fadøl
        fredagsweehoo.setPris(æblebrus, 15, 0);
        fredagsweehoo.setPris(chips, 10, 0);
        fredagsweehoo.setPris(peanuts, 15, 0);
        fredagsweehoo.setPris(cola, 15, 0);
        fredagsweehoo.setPris(nikoline, 15, 0);
        fredagsweehoo.setPris(sevenup, 15, 0);
        fredagsweehoo.setPris(vand, 10, 0);
        fredagsweehoo.setPris(ølpølser, 30, 1);
        //spiritus
        fredagsweehoo.setPris(whisky4350, 499, 0);
        fredagsweehoo.removeVare(whisky4cl);
        fredagsweehoo.setPris(uegesplint, 300, 0);
        fredagsweehoo.setPris(megensplint, 350, 0);
        fredagsweehoo.setPris(whiskeyplusbrikker, 80, 0);
        fredagsweehoo.setPris(liquorofAarhus, 175, 0);
        fredagsweehoo.setPris(lyng50cl, 350, 0);
        fredagsweehoo.setPris(lyng4cl, 40, 0);
        //Kulsyre
        fredagsweehoo.setPris(firekg, ((400 / 6.0) * 4), 0);
        fredagsweehoo.setPris(tikg, ((400 / 6.0) * 10), 0);
        //beklædningændringer
        fredagsweehoo.setPris(polo, 100, 0);
        fredagsweehoo.setPris(cap, 30, 0);
        //sampakningerændringer
        fredagsweehoo.setPris(gaveæske2øl2glas, 110, 0);
        fredagsweehoo.setPris(gaveæske4øl, 140, 0);
        fredagsweehoo.setPris(trækasse6øl6glas, 350, 0);
        fredagsweehoo.setPris(træasse12øl, 410, 0);
        fredagsweehoo.setPris(papkasse12øl, 370, 0);


        Udlejning udlejning = salgController.createUdlejning(LocalDate.now(), LocalDate.now(), "Mathias", "51482610", "alin_con", fredagsweehoo);
        udlejning.createOrdreLinje(5, extraPilsnerFadøl);
        Udlejning udlejning1 = salgController.createUdlejning(LocalDate.now(), LocalDate.now(), "Mathias", "51482610", "alin_con", fredagsweehoo);
        udlejning1.createOrdreLinje(5, extraPilsnerFadøl);
        salgController.afslutUdlejning(udlejning, Betalingsform.DANKORT);


        vareController.addVareGruppeToPrisliste(udlejningprisliste, fustage, 775, 0);
        vareController.addVareGruppeToPrisliste(udlejningprisliste, anlæg, 500, 0);

        //fustageændringer
        udlejningprisliste.setPris(klosterbrygFustage30, ((775 / 20.0) * 30), 0);
        udlejningprisliste.setPris(jazz30, ((625 / 25.0) * 30), 0);
        udlejningprisliste.setPris(extrapilsner, 575, 0);
        udlejningprisliste.setPris(extrapilsner30, ((575 / 25.1) * 30), 0);
        udlejningprisliste.setPris(celebration30, ((775 / 20.0) * 30), 0);
        udlejningprisliste.setPris(blondie, 700, 0);
        udlejningprisliste.setPris(blondie30, ((700 / 20.0) * 30), 0);
        udlejningprisliste.setPris(forårsbrygFustage30, ((775 / 20.0) * 30), 0);
        udlejningprisliste.setPris(indianale30, ((775 / 20.0) * 30), 0);
        udlejningprisliste.setPris(julebryg30, ((775 / 20.0) * 30), 0);
        udlejningprisliste.setPris(imperialstout30, ((775 / 20.0) * 30), 0);
        //Kulsyre
        udlejningprisliste.setPris(firekg, ((400 / 6.0) * 4), 0);
        udlejningprisliste.setPris(tikg, ((400 / 6.0) * 10), 0);
        //anlægændringer
        udlejningprisliste.setPris(enhane, 250, 0);
        udlejningprisliste.setPris(tohaner, 400, 0);
        udlejningprisliste.setPris(krus, 60, 0);

        vareController.saveStorage();
    }


}

