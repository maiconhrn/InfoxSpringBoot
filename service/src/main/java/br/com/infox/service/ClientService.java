package br.com.infox.service;

import br.com.infox.model.entity.Address;
import br.com.infox.model.entity.Client;
import br.com.infox.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import static br.com.infox.util.BeanUtil.copyNonNullProperties;

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

    public Client saveClient(@NotNull(message = "Client cant be null") Client client) {
        return clientRepository.save(client);
    }

    public Client findById(@NotNull Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Client with id: " + id + " not found"));
    }

    public Address findAddressByClientId(@NotNull Long clientId) {
        return clientRepository.findAddressByClientId(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client with ID: " + clientId + " no found"));
    }

    public Client update(@NotNull Client clientToUpdate, @NotNull Client clientWithNewValues) {
        copyNonNullProperties(clientWithNewValues, clientToUpdate);

        return clientRepository.save(clientToUpdate);
    }

    public Client update(Long clientId, Client clientWithNewValues) {
        Client clientToUpdate = findById(clientId);

        if (clientToUpdate == null) {
            throw new EntityNotFoundException("Client with id: " + clientId + " not found");
        }

        return update(clientToUpdate, clientWithNewValues);
    }
}
