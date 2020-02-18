package br.com.infox.api.controller;

import br.com.infox.api.dto.ClientDTO;
import br.com.infox.exceptions.ClientException;
import br.com.infox.models.Client;
import br.com.infox.repository.ClientRepository;
import br.com.infox.service.ClientService;
import br.com.infox.util.ClientUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@RestController
@RequestMapping(path = "/api/clients")
@Transactional
public class ClientController {

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    private ClientDTO save(Client client) {
        if (client != null) {
            try {
                client = clientService.saveClient(client);
                return ClientUtil.toDto(client);
            } catch (Exception e) {
                throw new ClientException("Erro ao salvar usuário" + e.getMessage());
            }
        }

        return new ClientDTO();
    }

    @GetMapping
    public ResponseEntity<PagedResources<Resource<ClientDTO>>> listAll(
            Pageable pageable, PagedResourcesAssembler<ClientDTO> assembler) {
        return new ResponseEntity<>(
                assembler.toResource(
                        clientRepository.findAll(pageable).map(ClientUtil::toDto),
                        ControllerLinkBuilder.linkTo(ClientController.class).withSelfRel()
                ), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> getById(@NotNull @PathVariable("clientId") Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) {
            throw new ClientException("Client with id: " + clientId + " not found");
        }

        return new ResponseEntity<>(ClientUtil.toDto(client), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> register(@NotNull @Valid @RequestBody ClientDTO clientDTO) {
        Client user = ClientUtil.fill(clientDTO);
        return new ResponseEntity<>(save(user), HttpStatus.OK);
    }

    @PutMapping(path = "/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> update(@NotNull @PathVariable("clientId") Long clientId, @NotNull @Valid @RequestBody ClientDTO clientDTO) {
        Client clientUpdated = clientService.update(clientId, ClientUtil.fill(clientDTO));
        return new ResponseEntity<>(ClientUtil.toDto(clientUpdated), HttpStatus.OK);
    }
}
