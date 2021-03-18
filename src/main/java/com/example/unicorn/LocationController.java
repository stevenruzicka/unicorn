package com.example.unicorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LocationController {

    @Autowired
    LocationRepository locationRepo;
    @Autowired
    UnicornRepository unicornRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index(Model m) {
        m.addAttribute("unicorns", unicornRepository.findAll());
        m.addAttribute("locations", locationRepo.findAll());
        return "locations";
    }

    @RequestMapping(value="/locations", method=RequestMethod.POST)
    public RedirectView create(@RequestParam String name) {
        Location newLocation = new Location(name);
        locationRepo.save(newLocation);
        return new RedirectView("/");
    }

    @RequestMapping(value="/locations/{locationId}/unicorns", method=RequestMethod.POST)
    public RedirectView addUnicorn(@PathVariable long locationId,
                                    @RequestParam long unicornId){
        Unicorn newUnicorn = unicornRepository.findById(unicornId).get();
        newUnicorn.location = locationRepo.findById(locationId).get();
        unicornRepository.save(newUnicorn);
        return new RedirectView("/");

    }
}