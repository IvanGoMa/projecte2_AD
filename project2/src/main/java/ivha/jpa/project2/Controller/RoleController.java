package ivha.jpa.project2.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import ivha.jpa.project2.DTO.ErrorDTO;
import ivha.jpa.project2.DTO.RoleRequestDTO;
import ivha.jpa.project2.Model.Role;
import ivha.jpa.project2.Repository.RoleRepository;
import ivha.jpa.project2.Model.Name;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleRepository roleRepo;

    public RoleController(RoleRepository roleRepo){
        this.roleRepo = roleRepo;
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody RoleRequestDTO roleRequest){
        try{
            Role role = new Role(Name.valueOf(roleRequest.getName()), roleRequest.getDescription());
            Role saved = roleRepo.save(role);
            return ResponseEntity.ok(saved);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id){
        try{
            roleRepo.deleteById(id);
            return ResponseEntity.ok("Rol esborrat");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}