package com.pasteruk.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
    @Column
    private Integer points;

//    private Integer facultyId;
//    private Integer userId;
//    private Integer subjectId;

    public Rating() {
    }

    public Rating(Integer id) {
        this.id = id;
    }

    public Rating(Faculty faculty, User user, Subject subject, Integer points) {
        this.faculty = faculty;
        this.user = user;
        this.subject = subject;
        this.points = points;
    }

    public Rating(Integer id, Faculty faculty, User user, Subject subject, Integer points) {
        this.id = id;
        this.faculty = faculty;
        this.user = user;
        this.subject = subject;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id.equals(rating.id) && faculty.equals(rating.faculty) && user.equals(rating.user) && subject.equals(rating.subject) && points.equals(rating.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, user, subject, points);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", faculty=" + faculty +
                ", user=" + user +
                ", subject=" + subject +
                ", points=" + points +
                '}';
    }
}