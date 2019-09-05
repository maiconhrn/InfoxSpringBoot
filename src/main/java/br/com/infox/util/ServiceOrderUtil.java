package br.com.infox.util;

import br.com.infox.models.ServiceOrder;
import br.com.infox.webservice.dto.ServiceOrderDTO;

/**
 * @author Maicon
 */
public class ServiceOrderUtil {

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

}
