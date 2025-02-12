package eu.mithril.invoiceservice.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebpageController {

    @GetMapping("/")
    public String homepage(
            Model model,
            @RequestParam(required = false, defaultValue = "stranger") String username
    ) {
        model.addAttribute("username", username);
        model.addAttribute("currentDate", LocalDateTime.now());
        return "index.html";
    }
}
