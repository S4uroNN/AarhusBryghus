package application.model;

import application.controller.SalgController;
import application.controller.VareController;

import storage.Storage;

import javax.swing.*;

import static application.model.Betalingsform.*;
import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    VareController vareController = VareController.getTestController();
    SalgController salgController = SalgController.getTestController();

    VareGruppe fadøl = vareController.createVareGruppe("Fadøl, 40cl", 0);
    VareGruppe flaske = vareController.createVareGruppe("Flaske", 0);
    VareGruppe glas = vareController.createVareGruppe("Glas", 0);

    Vare klosterbrygFlaske = vareController.createVare("Klosterbryg Flaske");
    Vare forårsbrygFadøl = vareController.createVare("Forårsbryg Fadøl");
    Vare glasUansetStørrelse = vareController.createVare("Glas");

    Prisliste fredagsweehoo = vareController.createPrisliste("Fredags Cafe");

    KontantRabat rabat = new KontantRabat(10);
    ProcentRabat prabat = new ProcentRabat(10);


    Salg salgMedOrdrelinjer = new Salg(fredagsweehoo);
    Ordrelinje forårsbryg = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
    Salg salgUdenOrdrelinjer = new Salg(fredagsweehoo);

    Ordrelinje klosterOrdrelinje;
    Ordrelinje forårOrdrelinje;
    Ordrelinje glasOrdrelinje;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 40, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,glas,15,0);

        salgMedOrdrelinjer = salgController.createSalg(fredagsweehoo);
        klosterOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(5,klosterbrygFlaske);
        forårOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
        glasOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(3, glasUansetStørrelse);

        salgUdenOrdrelinjer = new Salg(fredagsweehoo);
    }

    @org.junit.jupiter.api.Test
    void createOrdreLinje() {
        Ordrelinje enKloster = salgMedOrdrelinjer.createOrdreLinje(1,klosterbrygFlaske);
        assertEquals(4,salgMedOrdrelinjer.getOrdrelinjer().size());
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(enKloster));

        Ordrelinje tiKloster = salgMedOrdrelinjer.createOrdreLinje(10,klosterbrygFlaske);
        assertEquals(5,salgMedOrdrelinjer.getOrdrelinjer().size());
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(tiKloster));
    }

    @org.junit.jupiter.api.Test
    void setBetalingsform() {
        salgMedOrdrelinjer.setBetalingsform(DANKORT);
        assertEquals(DANKORT,salgMedOrdrelinjer.getBetalingsform());

        salgMedOrdrelinjer.setBetalingsform(MOBILEPAY);
        assertEquals(MOBILEPAY,salgMedOrdrelinjer.getBetalingsform());

        salgMedOrdrelinjer.setBetalingsform(KONTANT);
        assertEquals(KONTANT,salgMedOrdrelinjer.getBetalingsform());

        salgMedOrdrelinjer.setBetalingsform(REGNING);
        assertEquals(REGNING,salgMedOrdrelinjer.getBetalingsform());

        salgMedOrdrelinjer.setBetalingsform(KLIPPEKORT);
        assertEquals(KLIPPEKORT,salgMedOrdrelinjer.getBetalingsform());


    }

    @org.junit.jupiter.api.Test
    void getOrdrelinjer() {
        assertEquals(3,salgMedOrdrelinjer.getOrdrelinjer().size());
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(klosterOrdrelinje));
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(forårOrdrelinje));
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(glasOrdrelinje));
        assertEquals(0,salgUdenOrdrelinjer.getOrdrelinjer().size());
    }

    @org.junit.jupiter.api.Test
    void samletPris() {
        //TC1
        assertEquals(475,salgMedOrdrelinjer.samletPris());

        //TC2 - procent Rabat for salg
        salgMedOrdrelinjer.setRabat(prabat);
        assertEquals(427.5,salgMedOrdrelinjer.samletPris());

        //TC3 - fast Rabat for salg
        salgMedOrdrelinjer.setRabat(rabat);
        assertEquals(465,salgMedOrdrelinjer.samletPris());

        //TC4 - 0 rabat på salg, 10 kroner på ordrelinje
        salgMedOrdrelinjer.setRabat(null);
        forårOrdrelinje.setRabat(new KontantRabat(10));
        assertEquals(465,salgMedOrdrelinjer.samletPris());

        //TC4 - 0 rabat på salg, 10 procent på ordrelinje
        forårOrdrelinje.setRabat(new ProcentRabat(10));
        assertEquals(467,salgMedOrdrelinjer.samletPris());

        //TC6 - ingen orderlinje
        assertEquals(0,salgUdenOrdrelinjer.samletPris());

        //TC7- ingen orderlinje med procentrabat
        salgUdenOrdrelinjer.setRabat(new ProcentRabat(10));
        assertEquals(0,salgUdenOrdrelinjer.samletPris());


    }

    @org.junit.jupiter.api.Test
    void samletPrisKlip() {
        //TC1
       assertEquals(14, salgMedOrdrelinjer.samletPrisKlip());

       //TC2
       assertEquals(0,salgUdenOrdrelinjer.samletPrisKlip());
    }

    @org.junit.jupiter.api.Test
    void getBetalingsform() {
        salgMedOrdrelinjer.setBetalingsform(Betalingsform.DANKORT);
        //Assert
        assertEquals(Betalingsform.DANKORT,Betalingsform.DANKORT);
        salgMedOrdrelinjer.setBetalingsform(Betalingsform.KLIPPEKORT);
        //Assert
        assertEquals(Betalingsform.KLIPPEKORT,Betalingsform.KLIPPEKORT);
    }

    @org.junit.jupiter.api.Test
    void removeOrdrelinje() {
        salgMedOrdrelinjer.removeOrdrelinje(klosterOrdrelinje);
        assertEquals(2, salgMedOrdrelinjer.getOrdrelinjer().size());
        assertFalse(salgMedOrdrelinjer.getOrdrelinjer().contains(klosterOrdrelinje));
        salgMedOrdrelinjer.removeOrdrelinje(glasOrdrelinje);
        assertEquals(1,salgMedOrdrelinjer.getOrdrelinjer().size());
        assertFalse(salgMedOrdrelinjer.getOrdrelinjer().contains(glasOrdrelinje));
    }

    @org.junit.jupiter.api.Test
    void getRabat() {
        salgMedOrdrelinjer.setRabat(prabat);
        assertEquals(prabat,salgMedOrdrelinjer.getRabat());

        salgUdenOrdrelinjer.setRabat(null);
        assertEquals(null, salgUdenOrdrelinjer.getRabat());
    }

    @org.junit.jupiter.api.Test
    void setRabat() {
        salgMedOrdrelinjer.setRabat(prabat);
        assertEquals(prabat,salgMedOrdrelinjer.getRabat());

        salgUdenOrdrelinjer.setRabat(null);
        assertEquals(null, salgUdenOrdrelinjer.getRabat());

    }
}