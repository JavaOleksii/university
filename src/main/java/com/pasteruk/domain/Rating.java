package com.pasteruk.domain;

import java.util.Objects;

public class Rating {
    private Integer id;
    private Integer facultyId;
    private Integer applicantId;
    private Integer subjectId;
    private Integer points;

    public Rating() {
    }

    public Rating(Integer facultyId, Integer applicantId, Integer subjectId, Integer points) {
        this.facultyId = facultyId;
        this.applicantId = applicantId;
        this.subjectId = subjectId;
        this.points = points;
    }

    public Rating(Integer id, Integer facultyId, Integer applicantId, Integer subjectId, Integer points) {
        this.id = id;
        this.facultyId = facultyId;
        this.applicantId = applicantId;
        this.subjectId = subjectId;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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
        return id.equals(rating.id) && facultyId.equals(rating.facultyId) && applicantId.equals(rating.applicantId) && subjectId.equals(rating.subjectId) && points.equals(rating.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyId, applicantId, subjectId, points);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", applicantId=" + applicantId +
                ", subjectId=" + subjectId +
                ", points=" + points +
                '}';
    }
}