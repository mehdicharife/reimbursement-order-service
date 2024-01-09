package ma.ensias.a.gl.g1.reimbursementordermicroservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.Mission;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.Professor;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.ReimbursementOrder;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.domain.ReimbursementOrderDocumentGenerator;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.event.MissionCostCalculationVerifiedEvent;
import ma.ensias.a.gl.g1.reimbursementordermicroservice.service.ReimbursementOrderService;

@RabbitListener(queues = "${missionCostCalculationVerifiedQueueName}")
public class MissionCostCalculationVerifiedEventListener {

    @Autowired
    private ReimbursementOrderService reimbursementOrderService;

    @Value("${esb}")
    private String esb;

    @Autowired
    private RestTemplate restTemplate;


    @RabbitHandler
    public void react(MissionCostCalculationVerifiedEvent event) {
        ReimbursementOrder reimbursementOrder = new ReimbursementOrder();
        reimbursementOrder.setMissionId(event.getMissionId());
        reimbursementOrder.setProfessorId(event.getProfessorId());

        Professor professor = restTemplate.getForObject(esb + "professors/" + event.getProfessorId(), Professor.class);
        Mission mission = restTemplate.getForObject(esb + "/missions/" + event.getMissionId(), Mission.class);

        String reimbursementOrderDocumentFileName = ReimbursementOrderDocumentGenerator.generateReimbursementOrderDocument(mission, professor, null);
        reimbursementOrder.setFileName(reimbursementOrderDocumentFileName);

        reimbursementOrderService.savReimbursementOrder(reimbursementOrder);
    }
    
}
