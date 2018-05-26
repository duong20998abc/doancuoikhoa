package application.model;

import application.extension.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class ProductDetailModel {
    private int id;
    private String name;
    private String image;
    private int price;
    private int quantity;
    private String shortDesc;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdDate;

    private CategoryDataModel category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public CategoryDataModel getCategory() {
        return category;
    }

    public void setCategory(CategoryDataModel category) {
        this.category = category;
    }
}
