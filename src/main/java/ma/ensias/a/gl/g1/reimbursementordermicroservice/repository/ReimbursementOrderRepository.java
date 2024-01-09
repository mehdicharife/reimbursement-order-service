package ma.ensias.a.gl.g1.reimbursementordermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.ReimbursementOrder;

@Repository
public interface ReimbursementOrderRepository extends JpaRepository<ReimbursementOrder, Long>{

}
