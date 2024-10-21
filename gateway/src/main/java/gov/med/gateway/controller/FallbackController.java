package gov.med.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FallbackController {

    @GetMapping("/fallback")
    public String fallback() {
        return "error";
    }
}

