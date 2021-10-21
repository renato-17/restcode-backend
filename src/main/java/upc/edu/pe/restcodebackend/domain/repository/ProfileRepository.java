package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> findByEmail(String email);
}
