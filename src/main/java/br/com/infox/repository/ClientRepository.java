package br.com.infox.repository;

import br.com.infox.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maicon
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
