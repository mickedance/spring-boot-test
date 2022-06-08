package com.example.springboottest.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private  String lastName;

    @Column(nullable = false)
    private LocalDate registerDate;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Column(nullable = false, unique = true)
    private String email;

    boolean status;

    public Student() {
        this.registerDate = LocalDate.now();
    }

    public Student(String firstName, String lastName, LocalDate birthDay, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.email = email;
    }

    public Student(int id, String firstName, String lastName, LocalDate birthDay, String email) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && status == student.status && firstName.equals(student.firstName) && lastName.equals(student.lastName) && Objects.equals(registerDate, student.registerDate) && Objects.equals(birthDay, student.birthDay) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, registerDate, birthDay, email, status);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registerDate=" + registerDate +
                ", birthDay=" + birthDay +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
