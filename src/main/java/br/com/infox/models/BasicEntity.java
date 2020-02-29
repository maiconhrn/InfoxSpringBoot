package br.com.infox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Maicon
 */
public class BasicEntity<E extends Serializable> implements Persistable<Long> {

    private static final long serialVersionUID = -2187928984731943693L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonIgnore
    private Long id;

    /**
     * Versão utilizada pelo provider JPA para solução em concorrência
     */
    @JsonIgnore
    @Version
    private Long version;

    @Column
    @JsonIgnore
    private Boolean retired;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createOrUpdateTime;

    @JsonIgnore
    @OneToOne(optional = true)
    @JoinColumn
    private E previousVersion;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public Boolean getRetired() {
        return this.retired;
    }

    public void setRetired(final Boolean retired) {
        this.retired = retired;
    }

    public Date getCreateOrUpdateTime() {
        return this.createOrUpdateTime;
    }

    public void setCreateOrUpdateTime(final Date createOrUpdateTime) {
        this.createOrUpdateTime = createOrUpdateTime;
    }

    public E getPreviousVersion() {
        return this.previousVersion;
    }

    public void setPreviousVersion(final E previousVersion) {
        this.previousVersion = previousVersion;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public String toString() {
        return String.format("%s id: %s", this.getClass().getSimpleName(), this.getId());
    }
}
