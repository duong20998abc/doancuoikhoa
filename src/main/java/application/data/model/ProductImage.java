package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_product_image")
public class ProductImage {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "image")
    private String image;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
