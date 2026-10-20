package esprit.tn.hajermarsaoui4cce11.service;

import java.util.List;
import esprit.tn.hajermarsaoui4cce11.domain.Reservation;

public interface IReservationService {

    Reservation create(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    Reservation update(Long id, Reservation reservation);

    void deleteById(Long id);
}
