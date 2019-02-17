package br.com.infox.repository;

import br.com.infox.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maicon
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}
