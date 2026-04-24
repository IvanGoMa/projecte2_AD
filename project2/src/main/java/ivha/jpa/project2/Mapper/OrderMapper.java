package ivha.jpa.project2.Mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.OrderItemResponseDTO;
import ivha.jpa.project2.DTO.OrderRequestDTO;
import ivha.jpa.project2.DTO.OrderResponseDTO;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Model.OrderItem;
import ivha.jpa.project2.Model.OrderStatus;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequestDTO orderRequestDTO){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return new Order(orderRequestDTO.getOrderDate(), orderRequestDTO.getTotalAmount(), OrderStatus.PENDENT, true, now, now);
    }

    public OrderResponseDTO toOrderResponseDTO (Order order){
        OrderResponseDTO response = new OrderResponseDTO(order.getCustomer().getId(), order.getOrderDate(), order.getTotalAmount(), order.getOrderStatus());
        if (order.getOrderItems() != null){
            List<OrderItem> orderItems = order.getOrderItems();
            List<OrderItemResponseDTO> orderItemsResponse = new ArrayList<>();
            for (OrderItem o : orderItems){
                orderItemsResponse.add(new OrderItemResponseDTO(o.getQuantity(), o.getUnitPrice(), o.getProduct()));
            }
            response.setOrderItems(orderItemsResponse);
        }
        return response;

    }
}
