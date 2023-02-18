package com.pasteruk.domain;

import javax.persistence.*;
import javax.persistence.Lob;
import java.util.Objects;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer numberOfSeats;
    @Lob
    private String encodedImage;

    public Faculty() {
    }

    public Faculty(String name, Integer numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    public Faculty(Integer id, String name, Integer numberOfSeats) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id.equals(faculty.id) && name.equals(faculty.name) && numberOfSeats.equals(faculty.numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfSeats);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}