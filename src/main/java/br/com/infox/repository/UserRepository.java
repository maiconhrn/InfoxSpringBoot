package br.com.infox.repository;

import br.com.infox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Maicon
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstName LIKE CONCAT(:firstName, '%')")
    List<User> findAllWhereFirstNameLike(@Param("firstName") String firstName);

}
