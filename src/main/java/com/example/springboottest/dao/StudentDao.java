package com.example.springboottest.dao;

import com.example.springboottest.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    Student save(Student student);
    void remove(Student student);
    Optional<Student> findById(int id);
    List<Student>  findAll();
    List<Student> findByFirstName(String name) throws IllegalArgumentException;
    void update(Student student);
    void removeAll();
}
