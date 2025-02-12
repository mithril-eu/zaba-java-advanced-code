package eu.mithril.invoiceservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebpageController {

    @GetMapping("/")
    public String homepage() {
        return "index.html";
    }
}
