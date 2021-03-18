package com.example.unicorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class UnicornController {
    @Autowired
    private UnicornRepository unicornRepo;

    @RequestMapping(value="/unicorns", method=RequestMethod.GET)
    public String index(Model m) {
        m.addAttribute("unicorns", unicornRepo.findAll());
        return "unicornIndex";
    }

    @RequestMapping(value="/unicorns", method=RequestMethod.POST)
    public RedirectView create(
            @RequestParam String color,
            @RequestParam String name) {
        Unicorn newUnicorn = new Unicorn(color, name);
        unicornRepo.save(newUnicorn);
        return new RedirectView("/unicorns");
    }

}
