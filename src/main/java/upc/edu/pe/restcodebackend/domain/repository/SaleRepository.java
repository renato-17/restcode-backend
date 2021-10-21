package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
