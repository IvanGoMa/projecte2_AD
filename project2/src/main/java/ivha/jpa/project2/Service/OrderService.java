package ivha.jpa.project2.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.OrderItemResponseDTO;
import ivha.jpa.project2.DTO.OrderRequestDTO;
import ivha.jpa.project2.DTO.OrderResponseDTO;
import ivha.jpa.project2.Mapper.OrderMapper;
import ivha.jpa.project2.Model.Invoice;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Model.OrderItem;
import ivha.jpa.project2.Repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrderMapper mapper;

    public OrderService (OrderRepository repo, OrderMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        Order order = mapper.toOrder(orderRequest);
        Invoice invoice = new Invoice(orderRequest.getInvoiceNumber(), orderRequest.getIssueDate(), orderRequest.getTaxAmount(), orderRequest.getTotalWithTax());
        List <OrderItem> orderItems = new ArrayList<>();
        // TODO
        for (OrderItemResponseDTO oir: order.getOrderItems())
        order.setInvoice(null);
        try {
            repo.save(order);
            return mapper.toOrderResponseDTO(order);
        } catch (Exception e) {
            return null;
        }
        

    }

}
