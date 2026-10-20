package esprit.tn.hajermarsaoui4cce11;

import esprit.tn.hajermarsaoui4cce11.domain.Agence;
import esprit.tn.hajermarsaoui4cce11.domain.CategorieVehicule;
import esprit.tn.hajermarsaoui4cce11.domain.StatutVehicule;
import esprit.tn.hajermarsaoui4cce11.domain.Vehicule;
import esprit.tn.hajermarsaoui4cce11.service.IAgenceService;
import esprit.tn.hajermarsaoui4cce11.service.IVehiculeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HajerMarsaoui4Cce11ApplicationTests {

    @Autowired
    private IAgenceService agenceService;

    @Autowired
    private IVehiculeService vehiculeService;

    @Test
    void contextLoads() {
    }

    @Test
    void testServiceAgenceEtVehicule() {
        // 1. Tester la création d'une agence
        Agence agence = new Agence();
        agence.setNom("AutoLoc Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("Centre Urbain Nord");
        agence.setTelephone("71234567");
        Agence agenceSauvegardee = agenceService.create(agence);

        assertNotNull(agenceSauvegardee.getIdAgence());
        assertEquals("Tunis", agenceSauvegardee.getVille());

        // 2. Tester la création d'un véhicule rattaché à cette agence
        Vehicule v = new Vehicule();
        v.setImmatriculation("240-TUN-" + (System.currentTimeMillis() % 100000));
        v.setMarque("Renault");
        v.setModele("Clio");
        v.setCategorie(CategorieVehicule.CITADINE);
        v.setTarifJournalier(new BigDecimal("95.00"));
        v.setStatut(StatutVehicule.DISPONIBLE);
        v.setAgence(agenceSauvegardee);

        Vehicule vehiculeSauvegarde = vehiculeService.create(v);
        assertNotNull(vehiculeSauvegarde.getIdVehicule());

        // 3. Tester la lecture
        List<Vehicule> vehicules = vehiculeService.findAll();
        assertFalse(vehicules.isEmpty());

        System.out.println(">>> TEST RÉUSSI : Agence ID=" + agenceSauvegardee.getIdAgence()
                + ", Véhicule ID=" + vehiculeSauvegarde.getIdVehicule());
    }
}
