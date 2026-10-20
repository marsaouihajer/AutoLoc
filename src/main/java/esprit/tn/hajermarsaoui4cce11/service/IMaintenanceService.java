package esprit.tn.hajermarsaoui4cce11.service;

import java.util.List;
import esprit.tn.hajermarsaoui4cce11.domain.Maintenance;

public interface IMaintenanceService {

    Maintenance create(Maintenance maintenance);

    Maintenance findById(Long id);

    List<Maintenance> findAll();

    Maintenance update(Long id, Maintenance maintenance);

    void deleteById(Long id);
}
