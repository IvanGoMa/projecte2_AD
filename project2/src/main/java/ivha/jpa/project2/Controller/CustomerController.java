package ivha.jpa.project2.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import ivha.jpa.project2.DTO.AddressRequestDTO;
import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.DTO.ErrorDTO;
import ivha.jpa.project2.Model.Address;
import ivha.jpa.project2.Service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    // 3c Esborrar totes les adreces d'un customer
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAdresses(@PathVariable int id){
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

    // 3d Retorna tots els customers
   @GetMapping("/customers")
    public ResponseEntity<?> getCustomers(){
        try{
            List<CustomerResponseDTO> customers = service.getCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }

    //3a Retorna el customer amb les adresses modificades
    @PatchMapping("/customers/{id}/addresses")
    public ResponseEntity<?> addAddresses(@PathVariable int id, @RequestBody List<AddressRequestDTO> addresses){
        try{
            CustomerResponseDTO response = service.addAddress(id, addresses);
            if (response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "No s'ha trobat cap customer amb id: " + id));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    //3b Consulta UN customer per id, i retorna  l’email de l’usuari, el nom, cognom, telèfon i les direccions de tots els customers.
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id){
        try{
            CustomerResponseDTO response = service.getCustomerById(id);
            if (response == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "No s'ha trobat cap customer amb id: " + id));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
