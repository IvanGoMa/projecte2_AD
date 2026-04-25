package ivha.jpa.project2.Mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ivha.jpa.project2.DTO.OrderItemResponseDTO;
import ivha.jpa.project2.DTO.OrderRequestDTO;
import ivha.jpa.project2.DTO.OrderResponseDTO;
import ivha.jpa.project2.DTO.productResponseDTO;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Model.OrderItem;
import ivha.jpa.project2.Model.OrderStatus;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequestDTO orderRequestDTO){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return new Order(orderRequestDTO.getOrderDate(), 0, OrderStatus.PENDENT, true, now, now);
    }

    public OrderResponseDTO toOrderResponseDTO (Order order){
        // Creem el DTO
        OrderResponseDTO response = new OrderResponseDTO(order.getId(), order.getCustomer().getId(), order.getOrderDate(), order.getTotalAmount(), order.getOrderStatus());
        if (order.getOrderItems() != null){
            
            List<OrderItem> orderItems = order.getOrderItems();
            List<OrderItemResponseDTO> orderItemsResponse = new ArrayList<>();

            // Transformem els OrderItem al seu DTO
            for (OrderItem o : orderItems){
                OrderItemResponseDTO orderItemResponse = new OrderItemResponseDTO(o.getQuantity(), o.getUnitPrice());

                if (o.getProduct() != null){
                    // Transformem els productes d'orderItem al seu DTO
                    productResponseDTO product = new productResponseDTO(
                        o.getProduct().getNom(),
                        o.getProduct().getDescripcio(),
                        o.getProduct().getStock(),
                        o.getProduct().getPrice(),
                        o.getProduct().getRating(),
                        o.getProduct().getCondition()
                    );
                    orderItemResponse.setProduct(product);
                }
                orderItemsResponse.add(orderItemResponse);
            }
            response.setOrderItems(orderItemsResponse);
        }
        return response;

    }
}
