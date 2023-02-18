package com.pasteruk.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pasteruk.service.FacultiesDTOHelper;
import com.pasteruk.service.FacultiesService;


@Controller
public class FacultiesController {
    @Autowired
    FacultiesService facultiesService;

    @RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
    public ModelAndView createFaculty(
            @RequestParam MultipartFile image,
            @RequestParam String name,
            @RequestParam Integer numberOfSeats) throws IOException {
        facultiesService.save(FacultiesDTOHelper.createEntity(image, name, numberOfSeats));
        return new ModelAndView("redirect:/home");
    }
}