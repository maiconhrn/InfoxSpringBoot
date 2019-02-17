package br.com.infox.manipulators.builders;

import br.com.infox.entities.Client;
import br.com.infox.entities.ServiceOrder;
import br.com.infox.entities.User;
import br.com.infox.type.ServiceOrderType;
import br.com.infox.type.ServiceOrderSituationType;

import java.util.Date;

/**
 * @author Maicon
 */
public final class ServiceOrderBuilder {
    private Long id;
    private Date creationDate;
    private ServiceOrderType type;
    private ServiceOrderSituationType situation;
    private String equipament;
    private String defect;
    private String service;
    private Double value;
    private Client client;
    private User technician;

    private ServiceOrderBuilder() {
    }

    public static ServiceOrderBuilder aServiceOrder() {
        return new ServiceOrderBuilder();
    }

    public ServiceOrderBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ServiceOrderBuilder withCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ServiceOrderBuilder withType(ServiceOrderType type) {
        this.type = type;
        return this;
    }

    public ServiceOrderBuilder withSituation(ServiceOrderSituationType situation) {
        this.situation = situation;
        return this;
    }

    public ServiceOrderBuilder withEquipament(String equipament) {
        this.equipament = equipament;
        return this;
    }

    public ServiceOrderBuilder withDefect(String defect) {
        this.defect = defect;
        return this;
    }

    public ServiceOrderBuilder withService(String service) {
        this.service = service;
        return this;
    }

    public ServiceOrderBuilder withValue(Double value) {
        this.value = value;
        return this;
    }

    public ServiceOrderBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public ServiceOrderBuilder withTechnician(User technician) {
        this.technician = technician;
        return this;
    }

    public ServiceOrder build() {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setId(id);
        serviceOrder.setCreationDate(creationDate);
        serviceOrder.setType(type);
        serviceOrder.setSituation(situation);
        serviceOrder.setEquipament(equipament);
        serviceOrder.setDefect(defect);
        serviceOrder.setService(service);
        serviceOrder.setValue(value);
        serviceOrder.setClient(client);
        serviceOrder.setTechnician(technician);
        return serviceOrder;
    }
}
