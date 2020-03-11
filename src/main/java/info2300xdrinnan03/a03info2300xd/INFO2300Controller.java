package info2300xdrinnan03.a03info2300xd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class INFO2300Controller {
    @RequestMapping("/")
    public String index() {
        return "Xander's INFO2300 Assignment";
    }
}
