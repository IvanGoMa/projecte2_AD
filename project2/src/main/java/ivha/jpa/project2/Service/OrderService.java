package ivha.jpa.project2.Service;

import ivha.jpa.project2.Controller.ProductController;
import ivha.jpa.project2.Service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ivha.jpa.project2.DTO.OrderItemRequestDTO;
import ivha.jpa.project2.DTO.OrderRequestDTO;
import ivha.jpa.project2.DTO.OrderResponseDTO;
import ivha.jpa.project2.DTO.productRequestDTO;
import ivha.jpa.project2.Mapper.OrderItemMapper;
import ivha.jpa.project2.Mapper.OrderMapper;
import ivha.jpa.project2.Model.Customer;
import ivha.jpa.project2.Model.Invoice;
import ivha.jpa.project2.Model.Order;
import ivha.jpa.project2.Model.OrderItem;
import ivha.jpa.project2.Model.OrderStatus;
import ivha.jpa.project2.Model.Product;
import ivha.jpa.project2.Repository.CustomerRepository;
import ivha.jpa.project2.Repository.OrderRepository;
import ivha.jpa.project2.Repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final ProductService productService;
    private final ProductController productController;
    private final OrderRepository repo;
    private final ProductRepository productRepo;
    private final OrderMapper mapper;
    private final OrderItemMapper orderItemMapper;
    private final CustomerRepository customerRepo;

    public OrderService (OrderRepository repo, ProductRepository productRepo, OrderMapper mapper, OrderItemMapper orderItemMapper, CustomerRepository customerRepository, Controller.ProductController productController, Service.ProductService productService){
        this.repo = repo;
        this.productRepo = productRepo;
        this.mapper = mapper;
        this.orderItemMapper = orderItemMapper;
        customerRepo = customerRepository;
    }

    // Creació d'una order
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {

        // Transformem DTO a entitat
        Order order = mapper.toOrder(orderRequest);

        // Busquem si existeix el customer, si no retornem null
        Optional<Customer> optCustomer = customerRepo.findById(orderRequest.getCustomer_id());

        if (!optCustomer.isPresent()) return null;
        Customer customer = optCustomer.get();

        List <OrderItem> orderItems = new ArrayList<>();
        float total = 0;

        // Transformem tots els DTOs de OrderItem a entitat i anem sumant el preu al total
        for (OrderItemRequestDTO oir: orderRequest.getOrderItems()){
            orderItems.add(orderItemMapper.toOrderItem(oir));
            total += oir.getUnitPrice() * oir.getQuantity();
        }

        // Creem la factura, sumant els impostos (s'han de passar en %, és a dir, passar 21 per fer un 21% d'IVA per exemple)
        Invoice invoice = new Invoice(orderRequest.getInvoiceNumber(), orderRequest.getIssueDate(), orderRequest.getTaxAmount(), total + total * orderRequest.getTaxAmount() / 100);
        
        // Afegim tots els camps creats a l'order, que té els setters especials on son necessaris per vincular el objectes a la BD.
        order.setTotalAmount(total);
        order.setInvoice(invoice);
        order.setOrderItems(orderItems);
        order.setCustomer(customer);

        // Afegim a la BD i retornem el DTO
        try {
            Order order2 = repo.save(order);
            return mapper.toOrderResponseDTO(order2);
        } catch (Exception e) {
            return null;
        }
        

    }
    
    // Canvia l'estat de l'order de pendent a processat
    @Transactional
    public OrderResponseDTO processOrder(int id) {

        Optional<Order> optOrder = repo.findById(id);
        if (!optOrder.isPresent()) return null;
        Order order = optOrder.get();
        order.setOrderStatus(OrderStatus.PROCESSAT);
        repo.save(order);
        return mapper.toOrderResponseDTO(order);

    }

    //Afegeix items a un order i retorna la informacio de l'order i order_item
    @Transactional
    public OrderResponseDTO addItem(int id, List<productRequestDTO> items){

        Optional<Order> optOrder = repo.findById(id);
        if (!optOrder.isPresent()){return null;}

        for (productRequestDTO productRequest : items) {
            Optional<Product> optProd = productRepo.findById((long)productRequest.getId());
            if (!optProd.isPresent()){return null;} // si un producte no existeix returnem error

            OrderItem item = mapper.to
        }
    }

}
