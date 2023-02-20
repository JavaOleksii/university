package com.pasteruk;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pasteruk.dao.RatingRepository;
import com.pasteruk.dao.FacultyRepository;
import com.pasteruk.dao.UserRepository;
import com.pasteruk.domain.Rating;
import com.pasteruk.domain.Faculty;
import com.pasteruk.domain.User;
import com.pasteruk.domain.UserRole;
import com.pasteruk.service.RatingService;
import com.pasteruk.service.FacultiesService;
import com.pasteruk.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UniversityApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private FacultiesService facultiesService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void testSaveUser() {
        List<User> users = userRepository.findAll();
        assertThat(users, hasSize(0));

        User user = new User();
        user.setEmail("1@gmail.com");
        user.setFirstName("1");
        user.setLastName("1");
        user.setPassword("1");
        user.setPasswordConfirm("1");
        user.setRole(UserRole.ROLE_USER);

        userService.save(user);

        users = userRepository.findAll();
        assertThat(users, hasSize(1));

        User userFromDb = users.get(0);
        assertTrue(userFromDb.getEmail().equals(user.getEmail()));
        assertTrue(userFromDb.getFirstName().equals(user.getFirstName()));
        assertTrue(userFromDb.getLastName().equals(user.getLastName()));
        assertTrue(userFromDb.getRole().equals(user.getRole()));
    }

    @Test
    public void testFindByEmail() {
        List<User> users = userRepository.findAll();
        assertThat(users, hasSize(0));

        User user = new User();
        user.setEmail("myCustomEmail@gmail.com");
        user.setFirstName("2");
        user.setLastName("2");
        user.setPassword("2");
        user.setPasswordConfirm("2");
        user.setRole(UserRole.ROLE_USER);

        userRepository.save(user);

        users = userRepository.findAll();
        assertThat(users, hasSize(1));

        User findByEmail = userService.findByEmail(user.getEmail());

        assertTrue(findByEmail.getEmail().equals(user.getEmail()));
        assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
        assertTrue(findByEmail.getLastName().equals(user.getLastName()));
        assertTrue(findByEmail.getRole().equals(user.getRole()));
    }

    @Test
    public void testSaveFaculty() {
        List<Faculty> faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = new Faculty();
        faculty.setName("1");
        faculty.setNumberOfSeats(1);
        faculty.setEncodedImage("1");

        facultiesService.save(faculty);

        faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(1));

        Faculty facultyFromDb = faculties.get(0);
        assertTrue(facultyFromDb.getName().equals(faculty.getName()));
        assertTrue(facultyFromDb.getNumberOfSeats().equals(faculty.getNumberOfSeats()));
        assertTrue(facultyFromDb.getEncodedImage().equals(faculty.getEncodedImage()));
    }

    @Test
    public void testFindById() {
        List<Faculty> faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = new Faculty();
        faculty.setName("1");
        faculty.setNumberOfSeats(1);
        faculty.setEncodedImage("1");

        facultyRepository.save(faculty);

        faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(1));

        Faculty facultyFromDb = facultiesService.findById(faculties.get(0).getId());

        assertTrue(facultyFromDb.getName().equals(faculty.getName()));
        assertTrue(facultyFromDb.getNumberOfSeats().equals(faculty.getNumberOfSeats()));
        assertTrue(facultyFromDb.getEncodedImage().equals(faculty.getEncodedImage()));
    }

    @Test
    public void testGetAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = new Faculty();
        faculty.setName("1");
        faculty.setNumberOfSeats(1);
        faculty.setEncodedImage("1");

        Faculty faculty2 = new Faculty();
        faculty2.setName("12");
        faculty2.setNumberOfSeats(12);
        faculty2.setEncodedImage("12");

        facultyRepository.saveAll(Arrays.asList(faculty, faculty2));

        faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(2));

        List<Faculty> facultiesFromDb = facultiesService.getAllFaculties();

        assertTrue(facultiesFromDb.get(0).getName().equals(faculty.getName()));
        assertTrue(facultiesFromDb.get(0).getNumberOfSeats().equals(faculty.getNumberOfSeats()));
        assertTrue(facultiesFromDb.get(0).getEncodedImage().equals(faculty.getEncodedImage()));

        assertTrue(facultiesFromDb.get(1).getName().equals(faculty2.getName()));
        assertTrue(facultiesFromDb.get(1).getNumberOfSeats().equals(faculty2.getNumberOfSeats()));
        assertTrue(facultiesFromDb.get(1).getEncodedImage().equals(faculty2.getEncodedImage()));
    }

    @Test
    public void testAddToRating() {
        User user = new User();
        user.setEmail("1@gmail.com");
        user.setFirstName("1");
        user.setLastName("1");
        user.setPassword("1");
        user.setPasswordConfirm("1");
        user.setRole(UserRole.ROLE_USER);

        userService.save(user);

        User userFromDb = userRepository.findAll().stream().findFirst().get();

        List<Faculty> faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = new Faculty();
        faculty.setName("1");
        faculty.setNumberOfSeats(1);
        faculty.setEncodedImage("1");

        facultiesService.save(faculty);

        Faculty facultyFromDb = facultyRepository.findAll().stream().findFirst().get();

        Rating rating = new Rating();
        rating.setFaculty(facultyFromDb);
        rating.setUser(userFromDb);

        List<Rating> ratings = ratingRepository.findAll();
        assertThat(ratings, hasSize(0));

        ratingService.add(rating);

        ratings = ratingRepository.findAll();
        assertThat(ratings, hasSize(1));

        Rating ratingFromDb = ratings.get(0);

        assertTrue(ratingFromDb.getFaculty().equals(facultyFromDb));
        assertTrue(ratingFromDb.getUser().equals(userFromDb));
    }

    @Test
    public void testDeleteFromRating() {
        User user = new User();
        user.setEmail("1@gmail.com");
        user.setFirstName("1");
        user.setLastName("1");
        user.setPassword("1");
        user.setPasswordConfirm("1");
        user.setRole(UserRole.ROLE_USER);

        userService.save(user);

        User userFromDb = userRepository.findAll().stream().findFirst().get();

        List<Faculty> faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = new Faculty();
        faculty.setName("1");
        faculty.setNumberOfSeats(1);
        faculty.setEncodedImage("1");

        Faculty faculty2 = new Faculty();
        faculty2.setName("12");
        faculty2.setNumberOfSeats(12);
        faculty2.setEncodedImage("12");

        facultyRepository.saveAll(Arrays.asList(faculty, faculty2));

        List<Faculty> facultiesFromDb = facultyRepository.findAll();

        Rating rating = new Rating();
        rating.setFaculty(facultiesFromDb.get(0));
        rating.setUser(userFromDb);

        Rating rating2 = new Rating();
        rating2.setFaculty(facultiesFromDb.get(1));
        rating2.setUser(userFromDb);

        List<Rating> ratings = ratingRepository.findAll();
        assertThat(ratings, hasSize(0));

        ratingRepository.saveAll(Arrays.asList(rating, rating2));

        ratings = ratingRepository.findAll();
        assertThat(ratings, hasSize(2));

        ratingService.delete(ratings.get(0));

        ratings = ratingRepository.findAll();
        assertThat(ratings, hasSize(1));
    }

    @Test
    public void testGetAll() {
        User user = new User();
        user.setEmail("1@gmail.com");
        user.setFirstName("1");
        user.setLastName("1");
        user.setPassword("1");
        user.setPasswordConfirm("1");
        user.setRole(UserRole.ROLE_USER);

        userService.save(user);

        User userFromDb = userRepository.findAll().stream().findFirst().get();

        List<Faculty> faculties = facultyRepository.findAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = new Faculty();
        faculty.setName("1");
        faculty.setNumberOfSeats(1);
        faculty.setEncodedImage("1");

        Faculty faculty2 = new Faculty();
        faculty2.setName("12");
        faculty2.setNumberOfSeats(12);
        faculty2.setEncodedImage("12");

        facultyRepository.saveAll(Arrays.asList(faculty, faculty2));

        List<Faculty> facultiesFromDb = facultyRepository.findAll();

        Rating rating = new Rating();
        rating.setFaculty(facultiesFromDb.get(0));
        rating.setUser(userFromDb);

        Rating rating2 = new Rating();
        rating2.setFaculty(facultiesFromDb.get(1));
        rating2.setUser(userFromDb);

        List<Rating> ratings = ratingRepository.findAll();
        assertThat(ratings, hasSize(0));

        ratingRepository.saveAll(Arrays.asList(rating, rating2));

        List<Rating> ratingsFromDb = ratingService.getAll();
        assertThat(ratingsFromDb, hasSize(2));
    }
}