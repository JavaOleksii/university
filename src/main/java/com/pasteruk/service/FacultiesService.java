package com.pasteruk.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pasteruk.dao.FacultyRepository;
import com.pasteruk.domain.Faculty;

@Service
public class FacultiesService {
    private Logger logger = LoggerFactory.getLogger(FacultiesService.class);

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty save(Faculty faculty) {
        logger.info("Create faculty item {} : " + faculty);
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculties() {
        logger.info("Get all faculty items");
        return facultyRepository.findAll();
    }

    public Faculty findById(Integer id) {
        logger.info("Get faculty item by id: " + id);
        return facultyRepository.findById(id).get();
    }
}