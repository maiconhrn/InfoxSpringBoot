package br.com.infox.util;

import br.com.infox.api.dto.ServiceOrderDTO;
import br.com.infox.models.ServiceOrder;

import java.util.Date;

/**
 * @author Maicon
 */
public class ServiceOrderUtil {

    private ServiceOrderUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static ServiceOrderDTO toDto(ServiceOrder os) {
        ServiceOrderDTO osDto = new ServiceOrderDTO();

        osDto.setClient(ClientUtil.toDto(os.getClient()));
        osDto.setCreationDate(os.getCreationDate());
        osDto.setDefect(os.getDefect());
        osDto.setEquipament(os.getEquipament());
        osDto.setId(os.getId());
        osDto.setService(os.getService());
        osDto.setSituation(os.getSituation());
        osDto.setTechnician(UserUtil.toDto(os.getTechnician()));
        osDto.setType(os.getType());
        osDto.setValue(os.getValue());

        return osDto;
    }

    public static ServiceOrder fill(ServiceOrderDTO serviceOrderDTO) {
        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setClient(ClientUtil.fill(serviceOrderDTO.getClient()));
        serviceOrder.setCreationDate(new Date());
        serviceOrder.setDefect(serviceOrderDTO.getDefect());
        serviceOrder.setEquipament(serviceOrderDTO.getEquipament());
        serviceOrder.setService(serviceOrderDTO.getService());
        serviceOrder.setSituation(serviceOrderDTO.getSituation());
        serviceOrder.setTechnician(UserUtil.fill(serviceOrderDTO.getTechnician()));
        serviceOrder.setType(serviceOrderDTO.getType());
        serviceOrder.setValue(serviceOrderDTO.getValue());

        return serviceOrder;
    }

}
