package gov.med.security.web.repository;

import gov.med.security.web.core.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<String, Users> {
    Optional<Users> findByUsername(String username);
}
