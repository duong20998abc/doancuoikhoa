package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "tbl_cart")
public class Cart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartid")
    @Id
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "guid")
    private String guid;

    @Column(name = "price")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartProduct> listCartProducts = new ArrayList<>();

    public List<CartProduct> getListCartProducts() {
        return listCartProducts;
    }

    public void setListCartProducts(List<CartProduct> listCartProducts) {
        this.listCartProducts = listCartProducts;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
