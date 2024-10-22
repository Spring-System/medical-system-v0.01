package gov.med.security.web.repository;

import gov.med.security.web.core.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
        Optional<Client> findByClientId(String clientId);
}
