package Assignment03XDINFO2300.info2300a03xd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class INFO2300Controller {

    @RequestMapping("/")
    public String index() {
        return "Xander's INFO2300 Assignment!";
    }
}
