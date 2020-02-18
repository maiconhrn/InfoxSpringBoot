package br.com.infox.api.controller;

import br.com.infox.api.dto.AddressDTO;
import br.com.infox.exceptions.ClientException;
import br.com.infox.models.Address;
import br.com.infox.models.Client;
import br.com.infox.service.AddressService;
import br.com.infox.service.ClientService;
import br.com.infox.util.AddressUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static br.com.infox.util.BeanUtil.copyNonNullProperties;

/**
 * @author Maicon
 */
@RestController
@RequestMapping(path = {"/api/clients"})
@Transactional
public class AddressController {

    private final AddressService addressService;
    private final ClientService clientService;

    public AddressController(AddressService addressService, ClientService clientService) {
        this.addressService = addressService;
        this.clientService = clientService;
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<AddressDTO> getAddress(@NotNull @PathVariable("id") Long id) {
        Address address = clientService.findAddressByClientId(id);

        if (address == null) {
            throw new ClientException("Address for client with id: " + id + "not found");
        }

        return new ResponseEntity<>(AddressUtil.toDto(address), HttpStatus.OK);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<AddressDTO> updateAddres(@NotNull @PathVariable("id") Long id,
                                                   @NotNull @Valid @RequestBody AddressDTO addressDTO) {
        Client client = clientService.findById(id);
        Address addressToUpdate = client.getAddress();

        if (addressToUpdate == null) {
            throw new ClientException("Address for client with id: " + id + "not found");
        }

        Address addressWithNewValues = AddressUtil.fill(addressDTO);
        copyNonNullProperties(addressWithNewValues, addressToUpdate);

        return new ResponseEntity<>(AddressUtil.toDto(addressService.saveAddress(addressToUpdate)), HttpStatus.OK);
    }
}
