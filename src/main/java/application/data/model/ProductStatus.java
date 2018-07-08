package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_product_status")
public class ProductStatus {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "statusid")
    @Id
    private int statusid;

    @Column(name = "name")
    private String name;

    @Column(name = "shortdesc")
    private String shortDesc;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
