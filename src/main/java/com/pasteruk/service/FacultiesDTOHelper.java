package com.pasteruk.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import com.pasteruk.domain.Faculty;

public class FacultiesDTOHelper {
    public static Faculty createEntity(MultipartFile file, String name, Integer numberOfSeats) throws IOException {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setNumberOfSeats(numberOfSeats);
        faculty.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));
        return faculty;
    }
}