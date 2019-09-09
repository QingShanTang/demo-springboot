package org.qingshan.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaleController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "index";
    }
}
