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
    Vare klippekort = vareController.createVare("Klippekort");

    Salg salgMedOrdrelinjer = new Salg(fredagsweehoo);
    Ordrelinje forårsbryg = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
    Salg salgUdenOrdrelinjer = new Salg(fredagsweehoo);

    Ordrelinje klosterOrdrelinje;
    Ordrelinje forårOrdrelinje;
    Ordrelinje glasOrdrelinje;
    Ordrelinje klippekortOrdrelinje;

    @BeforeEach
    void setUp() {

        vareController.addVareToVareGruppe(klosterbrygFlaske, flaske);
        vareController.addVareToVareGruppe(forårsbrygFadøl, fadøl);
        vareController.addVareToVareGruppe(glasUansetStørrelse, glas);

        vareController.addVareGruppeToPrisliste(fredagsweehoo, fadøl, 40, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo, flaske, 70, 2);
        vareController.addVareGruppeToPrisliste(fredagsweehoo,glas,15,0);
        vareController.addVareToPrisliste(fredagsweehoo,klippekort,100,10);

        salgMedOrdrelinjer = salgController.createSalg(fredagsweehoo);
        klosterOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(5,klosterbrygFlaske);
        forårOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(2,forårsbrygFadøl);
        glasOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(3, glasUansetStørrelse);
        klippekortOrdrelinje = salgMedOrdrelinjer.createOrdreLinje(1,klippekort);
        salgMedOrdrelinjer.setBetalingsform(Betalingsform.DANKORT);


        salgUdenOrdrelinjer = new Salg(fredagsweehoo);
        Salg salg = salgController.createSalg(fredagsweehoo);
        salg.createOrdreLinje(1, klosterbrygFlaske);
        salg.setBetalingsform(Betalingsform.KLIPPEKORT);


    }

    @Test
    void getSolgteKlip() {

        System.out.println(salgController.getSolgteKlip(LocalDate.now(),LocalDate.now()));
        assertTrue(salgMedOrdrelinjer.getOrdrelinjer().contains(klosterOrdrelinje));

        assertEquals(10,salgController.getSolgteKlip(LocalDate.now(),LocalDate.now()));
    }

    @Test
    void getBrugteKlip(){
        assertEquals(2,salgController.getBrugteKlip(LocalDate.of(05,04,22),LocalDate.now()));
    }

    @Test
    void getOmsætning() {
        assertEquals(575, salgController.getOmsætning(LocalDate.now()));

    }
}