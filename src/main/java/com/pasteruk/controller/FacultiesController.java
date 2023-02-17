package com.pasteruk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pasteruk.domain.Faculty;
import com.pasteruk.service.FacultiesService;


@Controller
public class FacultiesController {
    @Autowired
    FacultiesService facultiesService;

    @RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
    public ModelAndView createFaculty(@Valid @ModelAttribute("faculty") Faculty faculty,
                                      BindingResult bindingResult) {
        facultiesService.save(faculty);
        return new ModelAndView("redirect:/home");
    }
}