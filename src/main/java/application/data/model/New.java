package application.data.model;


import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_new")
public class New {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "newid")
    @Id
    private int newId;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "new_link_img")
    private String img;
    @Column(name = "created_date")
    private Date createdDate;

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
