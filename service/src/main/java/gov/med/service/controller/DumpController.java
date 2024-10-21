package gov.med.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class DumpController {

    @GetMapping("/service1")
    public String dump() {
        return "Hello World!";
    }
}
