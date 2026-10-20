package esprit.tn.hajermarsaoui4cce11.service;

import static esprit.tn.hajermarsaoui4cce11.service.ValidationUtils.exigerNonNull;
import static esprit.tn.hajermarsaoui4cce11.service.ValidationUtils.exigerNonVide;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import esprit.tn.hajermarsaoui4cce11.domain.Employe;
import esprit.tn.hajermarsaoui4cce11.exception.ResourceNotFoundException;
import esprit.tn.hajermarsaoui4cce11.repository.IEmployeRepository;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe create(Employe employe) {
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle entité ne doit pas avoir d'identifiant");
        }
        valider(employe);
        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe employe) {
        Employe existant = findById(id);
        valider(employe);
        // recopie des attributs modifiables, sans toucher à l'identifiant ni aux collections inverses
        BeanUtils.copyProperties(employe, existant, "idEmploye");
        return employeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepository.deleteById(id);
    }

    private void valider(Employe e) {
        exigerNonVide(e.getNom(), "nom");
        exigerNonVide(e.getPrenom(), "prenom");
        exigerNonNull(e.getRole(), "role");
    }
}
