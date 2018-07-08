package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleid")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "shortdesc")
    private String desc;

    public Role() {

    }

    public Role(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
