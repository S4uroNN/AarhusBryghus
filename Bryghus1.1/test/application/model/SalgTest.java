package application.model;

import application.controller.VareController;
import storage.Storage;

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


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 40, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 4);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,glas,15,0);

        Salg salgMedOrdrelinjer = new Salg(fredagsweehoo);
        salgMedOrdrelinjer.createOrdreLinje(5,klosterbrygFlaske);
        salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
        salgMedOrdrelinjer.createOrdreLinje(3, glasUansetStørrelse);
        salgMedOrdrelinjer.setRabat(new ProcentRabat(10));

        Salg salgUdenOrdrelinjer = new Salg(fredagsweehoo);
    }

    @org.junit.jupiter.api.Test
    void createOrdreLinje() {

    }

    @org.junit.jupiter.api.Test
    void setBetalingsform() {
    }

    @org.junit.jupiter.api.Test
    void getOrdrelinjer() {
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