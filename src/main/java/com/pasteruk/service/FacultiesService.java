package com.pasteruk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pasteruk.dao.FacultyRepository;
import com.pasteruk.domain.Faculty;

@Service
public class FacultiesService {

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}