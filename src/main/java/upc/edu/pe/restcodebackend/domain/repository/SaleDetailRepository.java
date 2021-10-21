package upc.edu.pe.restcodebackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.pe.restcodebackend.domain.model.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long> {
}
