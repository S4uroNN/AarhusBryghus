package application.model;

import application.controller.VareController;
import storage.Storage;

import java.time.LocalDate;

import static application.model.Betalingsform.*;
import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    VareController vareController = VareController.getController();

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

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 40, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,glas,15,0);


        salgMedOrdrelinjer.createOrdreLinje(5,klosterbrygFlaske);
        salgMedOrdrelinjer.createOrdreLinje(3, glasUansetStørrelse);


    }

    @org.junit.jupiter.api.Test
    void createOrdreLinje() {

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

        //TC4 - 0 rabat på salg, 10 på ordrelinje


    }

    @org.junit.jupiter.api.Test
    void samletPrisKlip() {
    }

    @org.junit.jupiter.api.Test
    void getId() {
    }

    @org.junit.jupiter.api.Test
    void setId() {
    }

    @org.junit.jupiter.api.Test
    void getBetalingsform() {
    }

    @org.junit.jupiter.api.Test
    void removeOrdrelinje() {
    }

    @org.junit.jupiter.api.Test
    void getRabat() {
    }

    @org.junit.jupiter.api.Test
    void setRabat() {
    }
}