package br.com.infox.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Maicon
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "person_seq", sequenceName = "person_seq", initialValue = 1, allocationSize = 1)
abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Long id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 15, unique = true, nullable = false)
    private String cpf;
    @Column(name = "birty_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birtyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirtyDate() {
        return birtyDate;
    }

    public void setBirtyDate(Date birtyDate) {
        this.birtyDate = birtyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(cpf, person.cpf) &&
                Objects.equals(birtyDate, person.birtyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, cpf, birtyDate);
    }
}
