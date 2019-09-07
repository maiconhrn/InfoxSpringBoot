package br.com.infox.repository;

import br.com.infox.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Maicon
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstName LIKE CONCAT(:firstName, '%')")
    Optional<List<User>> findAllWhereFirstNameLike(@Param("firstName") String firstName);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.hashPassword = :password")
    Optional<User> login(@Param("username") String username, @Param("password") String password);
}
