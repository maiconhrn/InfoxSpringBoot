package br.com.infox.webservice.util;

import br.com.infox.entities.Address;
import br.com.infox.webservice.model.dto.AddressDTO;

/**
 * @author Maicon
 */
public class AddressUtil {
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
}
