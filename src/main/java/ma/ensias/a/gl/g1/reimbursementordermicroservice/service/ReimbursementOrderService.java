package ma.ensias.a.gl.g1.reimbursementordermicroservice.service;

import java.util.List;
import java.util.Optional;

import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.ReimbursementOrder;

public interface ReimbursementOrderService {

    List<ReimbursementOrder> getAllReimbursementOrders();
    Optional<ReimbursementOrder> getReimbursementOrderById(Long id);
    ReimbursementOrder savReimbursementOrder(ReimbursementOrder reimbursementOrder);
}
