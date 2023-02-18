package com.pasteruk.controller;


import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pasteruk.domain.Rating;
import com.pasteruk.domain.Faculty;
import com.pasteruk.domain.User;
import com.pasteruk.service.RatingService;
import com.pasteruk.service.FacultiesService;
import com.pasteruk.service.UserService;

@Controller
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private FacultiesService facultiesService;

    @RequestMapping(value = "/ratings", method = RequestMethod.GET)
    public ModelAndView getAllItems() {
        return getRatingItems();
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam String facultyId) {
        Faculty faculty = facultiesService.findById(Integer.parseInt(facultyId));

        Rating rating = new Rating();
        rating.setFaculty(faculty);
//        rating.setPurchaseDate(new Date());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        User user = userService.findByEmail(userEmail);
        rating.setUser(user);

        ratingService.add(rating);
        return getRatingItems();
    }

    @RequestMapping(value = "/rating", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam String id) {
        ratingService.delete(new Rating(Integer.parseInt(id.replaceAll("\\s", ""))));
        return getRatingItems();
    }

    private ModelAndView getRatingItems() {
        ModelAndView map = new ModelAndView("rating");
        map.addObject("ratingItems", ratingService.getAll());
        return map;
    }
}