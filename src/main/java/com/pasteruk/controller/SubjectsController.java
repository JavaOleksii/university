package com.pasteruk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pasteruk.domain.Subject;
import com.pasteruk.service.SubjectsService;

@Controller
public class SubjectsController {
    @Autowired
    SubjectsService subjectsService;

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public ModelAndView createSubject(@Valid @ModelAttribute("subject") Subject subject,
                                         BindingResult bindingResult) {
        subjectsService.save(subject);
        return new ModelAndView("redirect:/home");
    }
}