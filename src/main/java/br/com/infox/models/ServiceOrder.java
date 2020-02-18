package br.com.infox.models;

import br.com.infox.models.types.ServiceOrderSituationType;
import br.com.infox.models.types.ServiceOrderType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "service_order")
public class ServiceOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private ServiceOrderType type;
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private ServiceOrderSituationType situation;
    @Column(length = 25, nullable = false)
    private String equipament;
    @Column(length = 150, nullable = false)
    private String defect;
    @Column(length = 150)
    private String service;
    @Column(length = 50)
    private Double value;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "technician_id")
    private User technician;
    @Column(nullable = false)
    private Boolean retired = false;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getTechnician() {
        return technician;
    }

    public void setTechnician(User technician) {
        this.technician = technician;
    }

    public Boolean getRetired() {
        return retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(creationDate, that.creationDate) &&
                type == that.type &&
                situation == that.situation &&
                Objects.equals(equipament, that.equipament) &&
                Objects.equals(defect, that.defect) &&
                Objects.equals(service, that.service) &&
                Objects.equals(value, that.value) &&
                Objects.equals(client, that.client) &&
                Objects.equals(technician, that.technician) &&
                Objects.equals(retired, that.retired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, type, situation, equipament, defect, service, value, client, technician, retired);
    }
}
