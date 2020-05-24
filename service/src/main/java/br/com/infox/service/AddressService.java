package br.com.infox.service;

import br.com.infox.model.entity.Address;
import br.com.infox.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@Service
@Transactional
public class AddressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(@NotNull Address address) {
        return addressRepository.save(address);
    }

    public Address findById(@NotNull Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No address found with ID: " + id));
    }
}
