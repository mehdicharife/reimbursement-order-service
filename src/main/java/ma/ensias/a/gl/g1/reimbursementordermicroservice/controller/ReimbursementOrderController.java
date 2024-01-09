package ma.ensias.a.gl.g1.reimbursementordermicroservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.ReimbursementOrder;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.service.ReimbursementOrderService;

@RestController
@RequestMapping("/reimbursement-orders")
public class ReimbursementOrderController {

    private ReimbursementOrderService reimbursementOrderService;

    public ReimbursementOrderController(ReimbursementOrderService reimbursementOrderService) {
        this.reimbursementOrderService = reimbursementOrderService;
    }

    @GetMapping
    public List<ReimbursementOrder> getAllReimbursementOrders() {
        return this.reimbursementOrderService.getAllReimbursementOrders();
    }

    @GetMapping
    public ResponseEntity<Object> getReimbursementOrderById(Long id) {
        Optional<ReimbursementOrder> optionalReimbursementOrder = this.reimbursementOrderService.getReimbursementOrderById(id);
        if(optionalReimbursementOrder.isPresent()) {
            return new ResponseEntity<>(optionalReimbursementOrder.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReimbursementOrder(@RequestBody ReimbursementOrder reimbursementOrder) {
        ReimbursementOrder ret = this.reimbursementOrderService.savReimbursementOrder(reimbursementOrder);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
    
}
