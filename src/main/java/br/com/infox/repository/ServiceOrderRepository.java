package br.com.infox.repository;

import br.com.infox.models.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maicon
 */
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
