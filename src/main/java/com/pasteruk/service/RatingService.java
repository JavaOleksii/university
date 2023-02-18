package com.pasteruk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pasteruk.dao.RatingRepository;
import com.pasteruk.domain.Rating;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }
}