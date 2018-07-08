package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_cartproduct")
public class CartProduct {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartproduct_id")
    @Id
    private int id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartid")
    private Cart cart;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int price(){
        int result = this.amount* this.getProduct().getPrice();
        return result;
    }
}
