package br.com.infox.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client extends Person implements Serializable {

    @Column(length = 25, nullable = false)
    private String phone;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "endereco_id")
    private Address address;
    @OneToMany(mappedBy = "client")
    private List<ServiceOrder> serviceOrders;
    @Column(nullable = false)
    private Boolean retired = false;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
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
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(phone, client.phone) &&
                Objects.equals(address, client.address) &&
                Objects.equals(serviceOrders, client.serviceOrders) &&
                Objects.equals(retired, client.retired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone, address, serviceOrders, retired);
    }
}