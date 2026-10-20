package esprit.tn.hajermarsaoui4cce11.service;

import java.util.List;
import esprit.tn.hajermarsaoui4cce11.domain.Paiement;

/**
 * Lecture seule : la création et la suppression d'un paiement passent par le Contrat.
 */
public interface IPaiementService {

    Paiement findById(Long id);

    List<Paiement> findAll();
}
