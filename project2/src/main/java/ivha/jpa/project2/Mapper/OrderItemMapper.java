package ivha.jpa.project2.Mapper;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.OrderItemRequestDTO;
import ivha.jpa.project2.Model.OrderItem;

@Component
public class OrderItemMapper {

    public OrderItem toOrderItem(OrderItemRequestDTO request){
        return new OrderItem(request.getQuantity(), request.getUnitPrice());
    }
}
