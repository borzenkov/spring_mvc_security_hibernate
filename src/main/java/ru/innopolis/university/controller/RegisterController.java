package ru.innopolis.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.university.model.Duck;
import ru.innopolis.university.service.DuckService;

import javax.validation.Valid;

/**
 * Created by imac on 11.12.16.
 */
@Controller
public class RegisterController {

    @Autowired
    DuckService duckService;

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public ModelAndView newDuck(ModelMap model) {
        return new ModelAndView("register", "duck", new Duck());
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String saveDuck(@Valid @ModelAttribute("duck")Duck duck, BindingResult result,
                           ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }

        duckService.saveDuck(duck);

        model.addAttribute("success", "User " + duck.getName() + " registered successfully");

        return "registrationsuccess";
    }
}
