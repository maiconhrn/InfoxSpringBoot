package br.com.infox.util;

import br.com.infox.model.dto.AddressDTO;
import br.com.infox.model.entity.Address;

/**
 * @author Maicon
 */
public class AddressUtil {

    private AddressUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static AddressDTO toDto(Address address) {
        AddressDTO dto = new AddressDTO();

        dto.setCep(address.getCep());
        dto.setCity(address.getCity());
        dto.setComplement(address.getComplement());
        dto.setCountry(address.getCountry());
        dto.setId(address.getId());
        dto.setNeighborhood(address.getNeighborhood());
        dto.setNumber(address.getNumber());
        dto.setState(address.getState());
        dto.setStreet(address.getStreet());

        return dto;
    }

    public static Address fill(AddressDTO addressDTO) {
        Address address = new Address();

        if (addressDTO != null) {
            address.setCep(addressDTO.getCep());
            address.setCity(addressDTO.getCity());
            address.setComplement(addressDTO.getComplement());
            address.setCountry(addressDTO.getCountry());
            address.setId(addressDTO.getId());
            address.setNeighborhood(addressDTO.getNeighborhood());
            address.setNumber(addressDTO.getNumber());
            address.setState(addressDTO.getState());
            address.setStreet(addressDTO.getStreet());
        }

        return address;
    }
}
