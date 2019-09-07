package br.com.infox.service;

import br.com.infox.exceptions.ClientException;
import br.com.infox.models.Client;
import br.com.infox.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maicon
 */
@Service
@Transactional
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveUser(Client user) {
        if (user == null) {
            throw new ClientException("Could not save a null Client Object");
        }

        return clientRepository.save(user);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new ClientException("Nenhum client cadastrado com ID: " + id));
    }
}
