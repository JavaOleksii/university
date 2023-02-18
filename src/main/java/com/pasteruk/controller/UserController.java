package com.pasteruk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.pasteruk.domain.Subject;
import com.pasteruk.domain.User;
import com.pasteruk.service.SubjectsService;
import com.pasteruk.service.FacultiesService;
import com.pasteruk.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FacultiesService facultiesService;
    @Autowired
    private SubjectsService subjectsService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/home";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView welcome() {
        ModelAndView map = new ModelAndView("home");
        map.addObject("faculties", facultiesService.getAllFaculties());
        map.addObject("subjects", subjectsService.getAllSubjects());
        return map;
    }

    @RequestMapping(value = "/create-faculty", method = RequestMethod.GET)
    public String createFaculty() {
        return "createFaculty";
    }

    @RequestMapping(value ="/create-subject", method = RequestMethod.GET)
    public ModelAndView createSubject() {
        return new ModelAndView("createSubject", "subject", new Subject());
    }
}