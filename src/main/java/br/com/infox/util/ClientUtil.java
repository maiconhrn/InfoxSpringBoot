package br.com.infox.util;

import br.com.infox.models.Client;
import br.com.infox.webservice.dto.ClientDTO;

/**
 * @author Maicon
 */
public class ClientUtil {

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

    public static Client merge(ClientDTO clientDTO, Client client) {
        if (clientDTO != null && client != null) {
            client.setCpf(clientDTO.getCpf() != null ? clientDTO.getCpf() : client.getCpf());
            client.setAddress(clientDTO.getAddress() != null ? AddressUtil.fill(clientDTO.getAddress()) : client.getAddress());
            client.setLastName(clientDTO.getLastName() != null ? clientDTO.getLastName() : client.getLastName());
            client.setFirstName(clientDTO.getFirstName() != null ? clientDTO.getFirstName() : client.getFirstName());
            client.setBirthDate(clientDTO.getBirthDate() != null ? clientDTO.getBirthDate() : client.getBirthDate());
            client.setPhone(clientDTO.getPhone() != null ? clientDTO.getPhone() : client.getPhone());
        }

        return client;
    }
}
