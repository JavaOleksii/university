package com.pasteruk.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pasteruk.domain.User;

public class UserDTOHelper {
    public static User createEntity(MultipartFile file, String firstName, String lastName, String email) throws IOException {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
//        user.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));
        return user;
    }
}