package br.com.infox.repository;

import br.com.infox.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maicon
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
