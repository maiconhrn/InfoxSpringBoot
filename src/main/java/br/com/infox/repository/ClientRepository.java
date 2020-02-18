package br.com.infox.repository;

import br.com.infox.models.Address;
import br.com.infox.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Maicon
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT a FROM Client c INNER JOIN c.address a WHERE c.id = :clientId")
    Optional<Address> findAddressByClientId(@Param("clientId") Long clientId);
}
