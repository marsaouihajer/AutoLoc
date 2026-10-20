package esprit.tn.hajermarsaoui4cce11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import esprit.tn.hajermarsaoui4cce11.domain.Employe;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {
}
