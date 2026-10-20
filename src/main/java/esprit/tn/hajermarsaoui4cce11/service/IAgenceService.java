package esprit.tn.hajermarsaoui4cce11.service;

import java.util.List;
import esprit.tn.hajermarsaoui4cce11.domain.Agence;

public interface IAgenceService {

    Agence create(Agence agence);

    Agence findById(Long id);

    List<Agence> findAll();

    Agence update(Long id, Agence agence);

    void deleteById(Long id);
}
