package esprit.tn.hajermarsaoui4cce11.service;

import static esprit.tn.hajermarsaoui4cce11.service.ValidationUtils.exigerNonVide;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import esprit.tn.hajermarsaoui4cce11.domain.Client;
import esprit.tn.hajermarsaoui4cce11.exception.ResourceNotFoundException;
import esprit.tn.hajermarsaoui4cce11.repository.IClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle entité ne doit pas avoir d'identifiant");
        }
        valider(client);
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client update(Long id, Client client) {
        Client existant = findById(id);
        valider(client);
        // recopie des attributs modifiables, sans toucher à l'identifiant ni aux collections inverses
        BeanUtils.copyProperties(client, existant, "idClient", "reservations");
        return clientRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }

    private void valider(Client e) {
        exigerNonVide(e.getNom(), "nom");
        exigerNonVide(e.getPrenom(), "prenom");
        exigerNonVide(e.getEmail(), "email");
    }
}
