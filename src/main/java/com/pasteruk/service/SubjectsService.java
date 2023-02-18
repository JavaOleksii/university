package com.pasteruk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pasteruk.dao.SubjectRepository;
import com.pasteruk.domain.Subject;

@Service
public class SubjectsService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
}