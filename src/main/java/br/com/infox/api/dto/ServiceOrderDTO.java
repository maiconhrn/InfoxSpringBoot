package br.com.infox.api.dto;

import br.com.infox.models.types.ServiceOrderType;
import br.com.infox.models.types.ServiceOrderSituationType;
import org.springframework.hateoas.core.Relation;

import java.util.Date;

/**
 * @author Maicon
 */
@Relation(collectionRelation = "serviceOrders")
public class ServiceOrderDTO {
    private Long id;
    private Date creationDate;
    private ServiceOrderType type;
    private ServiceOrderSituationType situation;
    private String equipament;
    private String defect;
    private String service;
    private Double value;
    private ClientDTO client;
    private UserDTO technician;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ServiceOrderType getType() {
        return type;
    }

    public void setType(ServiceOrderType type) {
        this.type = type;
    }

    public ServiceOrderSituationType getSituation() {
        return situation;
    }

    public void setSituation(ServiceOrderSituationType situation) {
        this.situation = situation;
    }

    public String getEquipament() {
        return equipament;
    }

    public void setEquipament(String equipament) {
        this.equipament = equipament;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public UserDTO getTechnician() {
        return technician;
    }

    public void setTechnician(UserDTO technician) {
        this.technician = technician;
    }
}
