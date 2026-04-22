package ivha.jpa.project2.Model;


import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", unique = true)
    private Order order;

    private String invoiceNumber;
    private Timestamp issueDate;
    private float taxAmount;
    private float totalWithTax;

    public Invoice(String invoiceNumber, Timestamp issueDate, float taxAmount, float totalWithTax) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.taxAmount = taxAmount;
        this.totalWithTax = totalWithTax;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public Timestamp getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }
    public float getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }
    public float getTotalWithTax() {
        return totalWithTax;
    }
    public void setTotalWithTax(float totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    

}
