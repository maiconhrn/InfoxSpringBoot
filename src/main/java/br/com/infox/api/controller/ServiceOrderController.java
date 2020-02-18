package br.com.infox.api.controller;

import br.com.infox.api.dto.ClientDTO;
import br.com.infox.api.dto.ServiceOrderDTO;
import br.com.infox.api.dto.UserDTO;
import br.com.infox.exceptions.ServiceOrderException;
import br.com.infox.models.Client;
import br.com.infox.models.ServiceOrder;
import br.com.infox.models.User;
import br.com.infox.service.ClientService;
import br.com.infox.service.ServiceOrderService;
import br.com.infox.service.UserService;
import br.com.infox.util.ClientUtil;
import br.com.infox.util.ServiceOrderUtil;
import br.com.infox.util.UserUtil;
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

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Maicon
 */
@RestController
@Transactional
@RequestMapping(path = {"/api/service_orders"})
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;
    private final ClientService clientService;
    private final UserService userService;

    public ServiceOrderController(ServiceOrderService serviceOrderService, ClientService clientService,
                                  UserService userService) {
        this.serviceOrderService = serviceOrderService;
        this.clientService = clientService;
        this.userService = userService;
    }

    private ServiceOrderDTO save(ServiceOrder serviceOrder) {
        if (serviceOrder != null) {
            try {
                serviceOrder = serviceOrderService.saveServiceOrder(serviceOrder);
                return ServiceOrderUtil.toDto(serviceOrder);
            } catch (Exception e) {
                throw new ServiceOrderException("Error on atempt to save ServiceOrder" + e.getMessage());
            }
        }

        return new ServiceOrderDTO();
    }

    @GetMapping
    public ResponseEntity<PagedResources<Resource<ServiceOrderDTO>>> listAll(
            Pageable pageable, PagedResourcesAssembler<ServiceOrderDTO> assembler) {
        return new ResponseEntity<>(
                assembler.toResource(
                        serviceOrderService.findAll(pageable).map(ServiceOrderUtil::toDto),
                        ControllerLinkBuilder.linkTo(ServiceOrderController.class).withSelfRel()
                ), HttpStatus.OK);
    }

    @GetMapping("/{serviceOrderId}")
    public ResponseEntity<ServiceOrderDTO> getById(@NotNull @PathVariable Long serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderService.findById(serviceOrderId);

        if (serviceOrder == null) {
            throw new ServiceOrderException("Service Order with id: " + serviceOrderId + " not found");
        }

        return new ResponseEntity<>(ServiceOrderUtil.toDto(serviceOrder), HttpStatus.OK);
    }

    @GetMapping("/{serviceOrderId}/client")
    public ResponseEntity<ClientDTO> getClient(@NotNull @PathVariable Long serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderService.findById(serviceOrderId);

        if (serviceOrder == null) {
            throw new ServiceOrderException("Service Order with id: " + serviceOrderId + " not found");
        }

        return new ResponseEntity<>(ClientUtil.toDto(serviceOrder.getClient()), HttpStatus.OK);
    }

    @PutMapping("/{serviceOrderId}/client")
    public ResponseEntity<ClientDTO> updateClient(@NotNull @PathVariable Long serviceOrderId,
                                                  @NotNull @Valid @RequestBody ClientDTO clientDTO) {
        ServiceOrder serviceOrder = serviceOrderService.findById(serviceOrderId);

        if (serviceOrder == null) {
            throw new ServiceOrderException("Service Order with id: " + serviceOrderId + " not found");
        }

        Client clientUpdated = clientService.update(serviceOrder.getClient(), ClientUtil.fill(clientDTO));
        return new ResponseEntity<>(ClientUtil.toDto(clientUpdated), HttpStatus.OK);
    }

    @GetMapping("/{serviceOrderId}/technician")
    public ResponseEntity<UserDTO> getUser(@NotNull @PathVariable Long serviceOrderId) {
        ServiceOrder serviceOrder = serviceOrderService.findById(serviceOrderId);

        if (serviceOrder == null) {
            throw new ServiceOrderException("Service Order with id: " + serviceOrderId + " not found");
        }

        return new ResponseEntity<>(UserUtil.toDto(serviceOrder.getTechnician()), HttpStatus.OK);
    }

    @PutMapping("/{serviceOrderId}/technician")
    public ResponseEntity<UserDTO> updateUser(@NotNull @PathVariable Long serviceOrderId,
                                              @NotNull @Valid @RequestBody UserDTO userDTO) {
        ServiceOrder serviceOrder = serviceOrderService.findById(serviceOrderId);

        if (serviceOrder == null) {
            throw new ServiceOrderException("Service Order with id: " + serviceOrderId + " not found");
        }

        User userUpdated = userService.update(serviceOrder.getTechnician(), UserUtil.fill(userDTO));
        return new ResponseEntity<>(UserUtil.toDto(userUpdated), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceOrderDTO> register(@NotNull @Valid @RequestBody ServiceOrderDTO serviceOrderDTO) {
        ServiceOrder serviceOrder = ServiceOrderUtil.fill(serviceOrderDTO);

        return new ResponseEntity<>(save(serviceOrder), HttpStatus.OK);
    }
}
