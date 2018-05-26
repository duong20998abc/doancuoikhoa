package application.viewmodel.landing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemVM {
    private String text;
    private String link;

    private List<MenuItemVM> children;

    public MenuItemVM (String text, String link) {
        this.text = text;
        this.link = link;
        children = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MenuItemVM> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItemVM> children) {
        this.children = children;
    }
}
