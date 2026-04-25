package ivha.jpa.project2.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.OrderItemRequestDTO;
import ivha.jpa.project2.DTO.OrderRequestDTO;
import ivha.jpa.project2.DTO.OrderResponseDTO;
import ivha.jpa.project2.Mapper.OrderItemMapper;
import ivha.jpa.project2.Mapper.OrderMapper;
import ivha.jpa.project2.Model.Invoice;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Model.OrderItem;
import ivha.jpa.project2.Model.OrderStatus;
import ivha.jpa.project2.Repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final OrderItemMapper orderItemMapper;

    public OrderService (OrderRepository repo, OrderMapper mapper, OrderItemMapper orderItemMapper){
        this.repo = repo;
        this.mapper = mapper;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {

        Order order = mapper.toOrder(orderRequest);
        List <OrderItem> orderItems = new ArrayList<>();
        float total = 0;

        for (OrderItemRequestDTO oir: orderRequest.getOrderItems()){
            orderItems.add(orderItemMapper.toOrderItem(oir));
            total += oir.getUnitPrice() * oir.getQuantity();
        }

        order.setTotalAmount(total);

        Invoice invoice = new Invoice(orderRequest.getInvoiceNumber(), orderRequest.getIssueDate(), orderRequest.getTaxAmount(), total + total * orderRequest.getTaxAmount() / 100);
        
        
        order.setInvoice(invoice);
        order.setOrderItems(orderItems);
        try {
            repo.save(order);
            return mapper.toOrderResponseDTO(order);
        } catch (Exception e) {
            return null;
        }
        

    }

    public OrderResponseDTO processOrder(int id) {

        Optional<Order> optOrder = repo.findById(id);
        if (!optOrder.isPresent()) return null;
        Order order = optOrder.get();
        order.setOrderStatus(OrderStatus.PROCESSAT);
        repo.save(order);
        return mapper.toOrderResponseDTO(order);

    }

}
