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

    public OrderService (OrderRepository repo, ProductRepository productRepo, OrderMapper mapper, OrderItemMapper orderItemMapper, CustomerRepository customerRepository, ProductController productController, ProductService productService){
        this.repo = repo;
        this.productRepo = productRepo;
        this.mapper = mapper;
        this.orderItemMapper = orderItemMapper;
        customerRepo = customerRepository;
        this.productController = productController;
        this.productService = productService;
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
    public OrderResponseDTO addItemById(int id, List<Integer> itemsId){

        Optional<Order> optOrder = repo.findById(id);
        if (!optOrder.isPresent()){return null;}
        Order order = optOrder.get();

        for (Integer itemId : itemsId) {
            Optional<Product> optProd = productRepo.findById((long) itemId);
            if (!optProd.isPresent()){return null;} // si un producte no existeix returnem error
            
            Product product = optProd.get();
            OrderItem item = new OrderItem(order, product, 1, product.getPrice());//creem un order item amb quantitat 1 i el preu del producte
            order.getOrderItems().add(item); //afegim el item a l'order
            order.setTotalAmount(order.getTotalAmount() + product.getPrice()); //actualitzem el total de l'order
        }
        repo.save(order);
        return mapper.toOrderResponseDTO(order);
    }

    // Cancela un order, canviant el seu estat a cancelat
    @Transactional
    public OrderResponseDTO cancelOrder(int id){

        Optional<Order> optOrder = repo.findById(id);
        if (!optOrder.isPresent()){return null;}
        Order order = optOrder.get();
        if (order.getOrderStatus() == OrderStatus.PENDENT){ // nomes si esta pendent
            order.setOrderStatus(OrderStatus.CANCELAT);
        }else return null; // si no esta pendent no es pot cancel·lar
        repo.save(order);
        return mapper.toOrderResponseDTO(order);
    }
}
