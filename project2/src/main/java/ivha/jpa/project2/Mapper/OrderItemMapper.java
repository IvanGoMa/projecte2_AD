package ivha.jpa.project2.Mapper;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.OrderItemRequestDTO;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Model.OrderItem;
import ivha.jpa.project2.Model.Product;

@Component
public class OrderItemMapper {
    
    public OrderItemRequestDTO toOrderItemRequestDTO(OrderItem orderItem){
        return new OrderItemRequestDTO(orderItem.getQuantity(), orderItem.getUnitPrice(), orderItem.getProduct().getId(), orderItem.getOrder().getId());
    }

    public OrderItem toOrderItem(OrderItemRequestDTO orderItemRequestDTO, Product product, Order order){
        return new OrderItem(order, product, orderItemRequestDTO.getQuantity(), orderItemRequestDTO.getUnitPrice());
    }


}
