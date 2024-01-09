package ma.ensias.a.gl.g1.reimbursementordermicroservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.ensias.a.gl.g1.reimbursementordermicroservice.event.MissionCostCalculationVerifiedEvent;

@Configuration
public class RabbitMQConfig {

    @Value("${missionCostCalculationVerifiedExchangeName}")
    private String MISSION_COST_CALCULATION_VERIFIED_EXCHANGE_NAME;

    @Value("${missionCostCalculationVerifiedQueueName}")
    private String MISSION_COST_CALCULATION_VERIFIED_QUEUE_NAME;


    @Bean
    public Queue missionCostCalculationVerifiedQueue() {
        return QueueBuilder.durable(MISSION_COST_CALCULATION_VERIFIED_QUEUE_NAME).build();
    }

    @Bean
    public Binding missionCostCalculationVerifiedBinding(Queue missionCostCalculationVerifiedQueue) {
        return new Binding(
            missionCostCalculationVerifiedQueue.getName(),
            DestinationType.QUEUE,
            MISSION_COST_CALCULATION_VERIFIED_EXCHANGE_NAME,
            "",
            null
        );
    }


    @Bean
    public ClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> map = new HashMap<>();

        map.put(
            "ma.ensias.a.gl.g1.missioncostcalculationmicroservice.event.MissionCostCalculationVerifiedEvent",
            MissionCostCalculationVerifiedEvent.class
        );

        classMapper.setIdClassMapping(map);
        
        return classMapper;
    }


    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper, ClassMapper classMapper) {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter(objectMapper);
        messageConverter.setClassMapper(classMapper);
        return messageConverter;
    }
    
}
