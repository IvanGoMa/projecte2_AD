package ivha.jpa.project2.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ivha.jpa.project2.DTO.ErrorDTO;
import ivha.jpa.project2.DTO.OrderRequestDTO;
import ivha.jpa.project2.DTO.OrderResponseDTO;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }

    @PostMapping("/orders/{customer_id}")
    public ResponseEntity<?> postMethodName(@RequestBody OrderRequestDTO orderRequest) {
        try {
            OrderResponseDTO order = service.createOrder(orderRequest);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
        
    }
    



}
