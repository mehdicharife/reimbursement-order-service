package ma.ensias.a.gl.g1.reimbursementordermicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.ReimbursementOrder;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.repository.ReimbursementOrderRepository;

@Service
public class ReimbursementOrderServiceImpl implements ReimbursementOrderService {
    
    private ReimbursementOrderRepository reimbursementOrderRepository;

    public ReimbursementOrderServiceImpl(ReimbursementOrderRepository reimbursementOrderRepository) {
        this.reimbursementOrderRepository = reimbursementOrderRepository;
    }


    public List<ReimbursementOrder> getAllReimbursementOrders() {
        return this.reimbursementOrderRepository.findAll();
    }

    public Optional<ReimbursementOrder> getReimbursementOrderById(Long id) {
        return this.reimbursementOrderRepository.findById(id);
    }

    public ReimbursementOrder savReimbursementOrder(ReimbursementOrder reimbursementOrder) {
        return this.reimbursementOrderRepository.save(reimbursementOrder);
    }
}
