package br.com.infox.webservice.controller;

import br.com.infox.exceptions.ClientException;
import br.com.infox.models.Client;
import br.com.infox.repository.ClientRepository;
import br.com.infox.service.ClientService;
import br.com.infox.util.ClientUtil;
import br.com.infox.webservice.dto.ClientDTO;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maicon
 */
@RestController
@RequestMapping("/clients")
@Transactional
public class ClientController {

    private ClientRepository clientRepository;
    private ClientService clientService;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    private List<ClientDTO> toDto(List<Client> clients) {
        List<ClientDTO> clientsDTO = new ArrayList<>();
        clients.forEach(client -> clientsDTO.add(ClientUtil.toDto(client)));

        return clientsDTO;
    }

    private ClientDTO save(Client client) {
        if (client != null) {
            try {
                client = clientService.saveUser(client);
                return ClientUtil.toDto(client);
            } catch (Exception e) {
                throw new ClientException("Erro ao salvar usuário" + e.getMessage());
            }
        }

        return new ClientDTO();
    }

    @GetMapping("/")
    public List<ClientDTO> listAll() {
        List<Client> clients = clientRepository.findAll();

        return (clients != null && !clients.isEmpty()) ? toDto(clients) : new ArrayList<>();
    }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable("id") Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new ClientException("Client with id: " + id + " not found");
        }

        return ClientUtil.toDto(client);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDTO register(@RequestBody ClientDTO userDTO) {
        if (userDTO == null) {
            throw new ClientException("Cliente nulo");
        }

        Client user = ClientUtil.fill(userDTO);
        return save(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDTO update(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
        if (id == null || id < 1) {
            throw new ClientException("Erro ao atualizar client com ID inválido: " + id);
        }

        if (clientDTO == null) {
            throw new ClientException("Somente campos nulos para atualizar");
        }

        Client client = clientService.findById(id);
        ClientUtil.merge(clientDTO, client);

        return save(client);
    }
}
