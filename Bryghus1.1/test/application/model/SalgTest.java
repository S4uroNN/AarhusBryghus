package application.model;

import application.controller.VareController;
import storage.Storage;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SalgTest {
    VareController vareController = VareController.getTestController();

    VareGruppe fadøl = vareController.createVareGruppe("Fadøl, 40cl", 0);
    VareGruppe flaske = vareController.createVareGruppe("Flaske", 0);
    VareGruppe glas = vareController.createVareGruppe("Glas", 0);

    Vare klosterbrygFlaske = vareController.createVare("Klosterbryg Flaske");
    Vare forårsbrygFadøl = vareController.createVare("Forårsbryg Fadøl");
    Vare glasUansetStørrelse = vareController.createVare("Glas");

    Prisliste fredagsweehoo = vareController.createPrisliste("Fredags Cafe");

    Salg salgMedOrdrelinjer;
    Salg salgUdenOrdrelinjer;

    Ordrelinje klosterOrdrelinje;
    Ordrelinje forårOrdrelinje;
    Ordrelinje glasOrdrelinje;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 40, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,glas,15,0);

        salgMedOrdrelinjer = new Salg(fredagsweehoo);
        klosterOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(5,klosterbrygFlaske);
        forårOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
        glasOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(3, glasUansetStørrelse);

        salgUdenOrdrelinjer = new Salg(fredagsweehoo);
    }

    @org.junit.jupiter.api.Test
    void createOrdreLinje() {
        salgMedOrdrelinjer.createOrdreLinje(1,klosterbrygFlaske);
        assertEquals(4,salgMedOrdrelinjer.getOrdrelinjer().size());
        salgMedOrdrelinjer.createOrdreLinje(10,klosterbrygFlaske);
        assertEquals(5,salgMedOrdrelinjer.getOrdrelinjer().size());
    }

    @org.junit.jupiter.api.Test
    void setBetalingsform() {

    }

    @org.junit.jupiter.api.Test
    void getOrdrelinjer() {
        assertEquals( 4, salgMedOrdrelinjer.getOrdrelinjer());
    }

    @org.junit.jupiter.api.Test
    void samletPris() {
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
        salgMedOrdrelinjer.setBetalingsform(Betalingsform.DANKORT);
        //Assert
        assertEquals(Betalingsform.DANKORT,Betalingsform.DANKORT);
        salgMedOrdrelinjer.setBetalingsform(Betalingsform.KLIPPEKORT);
        //Assert
        assertEquals(Betalingsform.KLIPPEKORT,Betalingsform.KLIPPEKORT);
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