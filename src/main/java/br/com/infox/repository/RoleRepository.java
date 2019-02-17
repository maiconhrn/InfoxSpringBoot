package br.com.infox.repository;

import br.com.infox.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maicon
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
