package br.com.infox.service;

import br.com.infox.exceptions.ClientException;
import br.com.infox.models.Address;
import br.com.infox.models.Client;
import br.com.infox.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

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

    public Client saveClient(@NotNull Client client) {
        if (client == null) {
            throw new ClientException("Client cant be null");
        }

        return clientRepository.save(client);
    }

    public Client findById(@NotNull Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new ClientException("No client found with ID: " + id));
    }

    public Address findAddressByClientId(@NotNull Long clientId) {
        return clientRepository.findAddressByClientId(clientId)
                .orElseThrow(() -> new ClientException("Client with ID: " + clientId + " no found"));
    }
}
