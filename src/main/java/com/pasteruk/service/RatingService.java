package com.pasteruk.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pasteruk.dao.RatingRepository;
import com.pasteruk.domain.Rating;

@Service
public class RatingService {
    private Logger logger = LoggerFactory.getLogger(RatingService.class);
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAll() {
        logger.info("Get all rating items");
        return ratingRepository.findAll();
    }

    public void delete(Rating rating) {
        logger.info("Delete rating item {} : " + rating);
        ratingRepository.delete(rating);
    }

    public Rating add(Rating rating) {
        logger.info("Create new rating item {}: " + rating);
        return ratingRepository.save(rating);
    }
}