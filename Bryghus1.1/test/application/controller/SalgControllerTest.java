package application.controller;

import application.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SalgControllerTest {

    SalgController salgController = SalgController.getTestController();
    VareController vareController = VareController.getTestController();

    Prisliste fredagsweehoo = vareController.createPrisliste("Fredags Cafe");

    VareGruppe fadøl = vareController.createVareGruppe("Fadøl, 40cl", 0);
    VareGruppe flaske = vareController.createVareGruppe("Flaske", 0);
    VareGruppe glas = vareController.createVareGruppe("Glas", 0);

    Vare klosterbrygFlaske = vareController.createVare("Klosterbryg Flaske");
    Vare forårsbrygFadøl = vareController.createVare("Forårsbryg Fadøl");
    Vare glasUansetStørrelse = vareController.createVare("Glas");
    Vare klippekortVare = new Klippekort("Klippekort");

    Salg salgMedOrdrelinjer = new Salg(fredagsweehoo);
    Ordrelinje forårsbryg = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
    Salg salgUdenOrdrelinjer = new Salg(fredagsweehoo);

    Ordrelinje klosterOrdrelinje;
    Ordrelinje forårOrdrelinje;
    Ordrelinje glasOrdrelinje;
    Ordrelinje klippekort;

    @BeforeEach
    void setUp() {

        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 40, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,glas,15,0);
        vareController.addVareToPrisliste(fredagsweehoo,klippekortVare,100,10);

        salgMedOrdrelinjer = new Salg(fredagsweehoo);
        klosterOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(5,klosterbrygFlaske);
        forårOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
        glasOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(3, glasUansetStørrelse);
        klippekort = salgMedOrdrelinjer.createOrdreLinje(1,klippekortVare);
        salgMedOrdrelinjer.setBetalingsform(Betalingsform.DANKORT);


        salgUdenOrdrelinjer = new Salg(fredagsweehoo);
    }

    @Test
    void getSolgteKlip() {

        System.out.println(salgController.getSolgteKlip(LocalDate.now(),LocalDate.now()));
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(klippekort));

        assertEquals(10,salgController.getSolgteKlip(LocalDate.now(),LocalDate.now()));
    }

    @Test
    void getBrugteKlip() {
        assertEquals(100,salgController.getBrugteKlip(LocalDate.of(05,04,22),LocalDate.now()));
    }

    @Test
    void getOmsætning() {
    }
}