package eu.mithril.invoiceservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleController {

    @GetMapping("/")
    @ResponseBody
    public String homepage() {
        return "Example Hello World";
    }
}
