package esprit.tn.hajermarsaoui4cce11.service;

import static esprit.tn.hajermarsaoui4cce11.service.ValidationUtils.exigerFinApresDebut;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import esprit.tn.hajermarsaoui4cce11.domain.Maintenance;
import esprit.tn.hajermarsaoui4cce11.exception.ResourceNotFoundException;
import esprit.tn.hajermarsaoui4cce11.repository.IMaintenanceRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance create(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle entité ne doit pas avoir d'identifiant");
        }
        valider(maintenance);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance maintenance) {
        Maintenance existant = findById(id);
        valider(maintenance);
        // recopie des attributs modifiables, sans toucher à l'identifiant ni aux collections inverses
        BeanUtils.copyProperties(maintenance, existant, "idMaintenance");
        return maintenanceRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepository.deleteById(id);
    }

    private void valider(Maintenance e) {
        exigerFinApresDebut(e.getDateDebut(), e.getDateFin());
    }
}
