package com.example.microservice.persistence.service;

import com.example.microservice.persistence.dto.OrderMessage;
import com.example.microservice.persistence.dto.ProductMessage;
import com.example.microservice.persistence.dto.ResultMessage;
import com.example.microservice.persistence.dto.UserMessage;
import com.example.microservice.persistence.entity.Order;
import com.example.microservice.persistence.entity.Product;
import com.example.microservice.persistence.entity.User;
import com.example.microservice.persistence.enums.ResultStatus;
import com.example.microservice.persistence.repository.OrderRepository;
import com.example.microservice.persistence.repository.ProductRepository;
import com.example.microservice.persistence.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Persistence service
 */
@Slf4j
@Service
public class PersistenceService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    public PersistenceService(
            @Autowired UserRepository userRepository,
            @Autowired ProductRepository productRepository,
            @Autowired OrderRepository orderRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Executes USER flow based on provided parameters
     *
     * @param sessionId   {@link String}
     * @param userMessage {@link UserMessage}
     * @return {@link ResultMessage} with execution result
     * @throws Exception
     */
    public ResultMessage execute(final String sessionId, final UserMessage userMessage) throws Exception {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSessionId(sessionId);
        resultMessage.setOriginalMessage(objectMapper.writeValueAsString(userMessage));
        resultMessage.setResultStatus(ResultStatus.SUCCESS);

        switch (userMessage.getExecutionType()) {
            case INSERT, UPDATE:
                resultMessage.setResultMessage(objectMapper.writeValueAsString(userRepository.save(transform(userMessage))));
                break;
            case DELETE:
                userRepository.delete(transform(userMessage));
                break;
            default:
                resultMessage.setResultStatus(ResultStatus.ERROR);
                resultMessage.setResultMessage("Undefined persistence operation");
        }

        return resultMessage;
    }

    /**
     * Executes PRODUCT flow based on provided parameters
     *
     * @param sessionId      {@link String}
     * @param productMessage {@link ProductMessage}
     * @return {@link ResultMessage} with execution result
     * @throws Exception
     */
    public ResultMessage execute(final String sessionId, final ProductMessage productMessage) throws Exception {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSessionId(sessionId);
        resultMessage.setOriginalMessage(objectMapper.writeValueAsString(productMessage));
        resultMessage.setResultStatus(ResultStatus.SUCCESS);

        switch (productMessage.getExecutionType()) {
            case INSERT, UPDATE:
                resultMessage.setResultMessage(objectMapper.writeValueAsString(productRepository.save(transform(productMessage))));
                break;
            case DELETE:
                productRepository.delete(transform(productMessage));
                break;
            default:
                resultMessage.setResultStatus(ResultStatus.ERROR);
                resultMessage.setResultMessage("Undefined persistence operation");
        }

        return resultMessage;
    }

    /**
     * Executes ORDER flow based on provided parameters
     *
     * @param sessionId    {@link String}
     * @param orderMessage {@link OrderMessage}
     * @return {@link ResultMessage} with execution result
     * @throws Exception
     */
    public ResultMessage execute(final String sessionId, final OrderMessage orderMessage) throws Exception {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSessionId(sessionId);
        resultMessage.setOriginalMessage(objectMapper.writeValueAsString(orderMessage));
        resultMessage.setResultStatus(ResultStatus.SUCCESS);

        switch (orderMessage.getExecutionType()) {
            case INSERT, UPDATE:
                resultMessage.setResultMessage(objectMapper.writeValueAsString(orderRepository.save(transform(orderMessage))));
                break;
            case DELETE:
                orderRepository.delete(transform(orderMessage));
                break;
            default:
                resultMessage.setResultStatus(ResultStatus.ERROR);
                resultMessage.setResultMessage("Undefined persistence operation");
        }

        return resultMessage;
    }

    /**
     * Transforms provided message type into proper entity with mapped values
     *
     * @param userMessage {@link UserMessage}
     * @return {@link User} entity with mapped values
     */
    private User transform(final UserMessage userMessage) {
        User user = new User();
        user.setId(userMessage.getId());
        user.setName(userMessage.getName());
        user.setAlias(userMessage.getAlias());
        user.setAddress(userMessage.getAddress());

        return user;
    }

    /**
     * Transforms provided message type into proper entity with mapped values
     *
     * @param productMessage {@link ProductMessage}
     * @return {@link Product} entity with mapped values
     */
    private Product transform(final ProductMessage productMessage) {
        Product product = new Product();
        product.setId(productMessage.getId());
        product.setName(productMessage.getName());
        product.setDescription(productMessage.getDescription());
        product.setQuantity(productMessage.getQuantity());
        product.setPrice(productMessage.getPrice());

        return product;
    }

    /**
     * Transforms provided message type into proper entity with mapped values
     *
     * @param orderMessage {@link OrderMessage}
     * @return {@link Order} entity with mapped values
     */
    private Order transform(final OrderMessage orderMessage) {
        Order order = new Order();
        order.setId(orderMessage.getId());
        order.setUserId(orderMessage.getUserId());
        order.setProductId(orderMessage.getProductId());
        order.setQuantity(orderMessage.getQuantity());

        return order;
    }
}
