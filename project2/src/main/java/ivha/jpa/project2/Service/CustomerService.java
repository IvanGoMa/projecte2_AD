package ivha.jpa.project2.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.Mapper.CustomerMapper;
import ivha.jpa.project2.Model.Customer;
import ivha.jpa.project2.Repository.AddressRepository;
import ivha.jpa.project2.Repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final AddressRepository addressRepo;
    private final CustomerMapper mapper;

    public CustomerService (CustomerRepository customerRepo, AddressRepository addressRepo, CustomerMapper mapper){
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.mapper = mapper;
    }

    public boolean deleteAdresses(int id) {

        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()){
            addressRepo.deleteAllByCustomerId(id);
            return true;
        } else {
            return false;
        }
    }

    public List<CustomerResponseDTO> getCustomers() {
        
        List<Customer> customers = customerRepo.findAll();
        List<CustomerResponseDTO> response = new ArrayList<>();

        for (Customer c: customers){
            response.add(mapper.toCustomerResponseDTO(c));
        }

        return response;
    }

}
