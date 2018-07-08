package application.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminNewsController {

    @GetMapping(path = "/manage_news")
    public String newsAdmin() {
        return "admin/manage_news";
    }

}
