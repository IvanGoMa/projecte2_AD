package ivha.jpa.project2.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.DTO.ErrorDTO;
import ivha.jpa.project2.Service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAdresses(int id){
        try {
            if(service.deleteAdresses(id)){
                return ResponseEntity.ok("S'han esborrat les adresses del customer amb id " + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(),"No s'ha trobat cap customer amb id " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers(){
        try{
            List<CustomerResponseDTO> customers = service.getCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }
}
