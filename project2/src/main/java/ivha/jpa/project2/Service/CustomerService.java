package ivha.jpa.project2.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.AddressRequestDTO;
import ivha.jpa.project2.DTO.CustomerResponseDTO;
import ivha.jpa.project2.Mapper.AddressMapper;
import ivha.jpa.project2.Mapper.CustomerMapper;
import ivha.jpa.project2.Model.Address;
import ivha.jpa.project2.Model.Customer;
import ivha.jpa.project2.Repository.AddressRepository;
import ivha.jpa.project2.Repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final AddressRepository addressRepo;
    private final CustomerMapper mapper;
    private final AddressMapper addressMapper;

    // Injecció de dependències
    public CustomerService (CustomerRepository customerRepo, AddressRepository addressRepo, CustomerMapper mapper, AddressMapper addressMapper){
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
    }

    // Elimina les adreces de l'usuari si existeix
    @Transactional
    public boolean deleteAdresses(int id) {

        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()){
            addressRepo.deleteAllByCustomerId(id);
            return true;
        } else {
            return false;
        }
    }

    // Retorna tots els customers
    @Transactional
    public List<CustomerResponseDTO> getCustomers() {
        
        List<Customer> customers = customerRepo.findAll();
        List<CustomerResponseDTO> response = new ArrayList<>();

        // Transformem totes les entities de Customer al seu DTO
        for (Customer c: customers){
            response.add(mapper.toCustomerResponseDTO(c));
        }

        return response;
    }

    //Afegeix dirreccions a un customer 
    @Transactional
    public CustomerResponseDTO addAddress(int id, List<AddressRequestDTO> adresses){
        
        Optional<Customer> optCust = customerRepo.findById(id);
        if (!optCust.isPresent()){return null;}

        Customer customer = optCust.get();
        List<Address> addressList = new ArrayList<>();

        //Assignar fk de costumer a cada adressa
        for (AddressRequestDTO addressRequest : adresses) {
            Address address = addressMapper.toAddress(addressRequest);
            address.setCustomer(customer);  
            addressList.add(address);      
        }            
        customer.getAdresses().addAll(addressList); // afegeix sense elimina les que ya existeixen

        customerRepo.save(customer);
        
        return mapper.toCustomerResponseDTO(customer);
    }

    //Consultar un customer x id i retornar l'email de l'usuari associat i les addresses
    @Transactional
    public CustomerResponseDTO getCustomerById(int id){
        
        Optional<Customer> optCust = customerRepo.findById(id);
        if(!optCust.isPresent()){return null;}

        Customer cust = optCust.get();
        
        CustomerResponseDTO response = mapper.toCustomerUserEmailResponseDTO(cust);
        return response;
    }

}
