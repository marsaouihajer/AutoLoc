package esprit.tn.hajermarsaoui4cce11.service;

import static esprit.tn.hajermarsaoui4cce11.service.ValidationUtils.exigerNonVide;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import esprit.tn.hajermarsaoui4cce11.domain.Agence;
import esprit.tn.hajermarsaoui4cce11.exception.ResourceNotFoundException;
import esprit.tn.hajermarsaoui4cce11.repository.IAgenceRepository;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle entité ne doit pas avoir d'identifiant");
        }
        valider(agence);
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {
        Agence existant = findById(id);
        valider(agence);
        // recopie des attributs modifiables, sans toucher à l'identifiant ni aux collections inverses
        BeanUtils.copyProperties(agence, existant, "idAgence", "vehicules", "employes");
        return agenceRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        agenceRepository.deleteById(id);
    }

    private void valider(Agence e) {
        exigerNonVide(e.getNom(), "nom");
        exigerNonVide(e.getVille(), "ville");
        exigerNonVide(e.getAdresse(), "adresse");
    }
}
