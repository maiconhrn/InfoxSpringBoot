package br.com.infox.util;

import br.com.infox.model.dto.ClientDTO;
import br.com.infox.model.entity.Client;

/**
 * @author Maicon
 */
public class ClientUtil {

    private ClientUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static ClientDTO toDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        if (client != null) {
            clientDTO.setCpf(client.getCpf());
            clientDTO.setAddress(AddressUtil.toDto(client.getAddress()));
            clientDTO.setBirthDate(client.getBirthDate());
            clientDTO.setFirstName(client.getFirstName());
            clientDTO.setLastName(client.getLastName());
            clientDTO.setPhone(client.getPhone());
            clientDTO.setId(client.getId());
        }

        return clientDTO;
    }

    public static Client fill(ClientDTO clientDTO) {
        Client client = new Client();

        client.setCpf(clientDTO.getCpf());
        client.setAddress(AddressUtil.fill(clientDTO.getAddress()));
        client.setPhone(clientDTO.getPhone());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setId(clientDTO.getId());

        return client;
    }
}
