package com.example.springboottest.dao;

import com.example.springboottest.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DirtiesContext
public class StudentDaoTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void save_successfully() {
        Student expected = new Student("Mike", "Svensson", LocalDate.parse("1990-09-12"), "email@email.com");
        Student actual = studentDao.save(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void save_throws_exception_with_null_value() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            studentDao.save(null);
        });
    }

    @Test
    public void findBydId_should_return_equal_object_true() {
        Student studentData = new Student("Mike", "Svensson", LocalDate.parse("1990-09-12"), "email2@email.com");
        Student expected = studentDao.save(studentData);
        Student actual = studentDao.findById(expected.getId()).orElse(null);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void findBydId_should_return_no_object() {
        Student student = new Student("Mike", "Svensson", LocalDate.parse("1990-09-12"), "email2@email.com");
        Student expected = studentDao.save(student);
        Optional<Student> actual = studentDao.findById(expected.getId() + 1);
        Assertions.assertFalse(actual.isPresent());
    }

    @Test
    public void findAll_should_return_list_of_3_objects() {
        studentDao.removeAll();
        Student student1 = new Student("Mike1", "Svensson1", LocalDate.parse("1990-07-12"), "email1@email.com");
        Student student2 = new Student("Mike2", "Svensson2", LocalDate.parse("1990-08-12"), "email2@email.com");
        Student student3 = new Student("Mike3", "Svensson3", LocalDate.parse("1990-09-12"), "email3@email.com");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        studentDao.save(student1);
        studentDao.save(student2);
        studentDao.save(student3);
        Assertions.assertEquals(studentList, studentDao.findAll());
    }

    @Test
    public void removeAll_should_remove_all() {
        Student student1 = new Student("Mike1", "Svensson1", LocalDate.parse("1990-07-12"), "email1@email.com");
        studentDao.save(student1);
        studentDao.removeAll();
        List<Student> expected = new ArrayList<>();
        Assertions.assertEquals(expected, studentDao.findAll());
    }

    @Test
    public void findByFirstName_should_throw_exception_with_empty_string() {
        Assertions.assertThrows(IllegalArgumentException.class , () -> studentDao.findByFirstName(""));
    }

    @Test
    public void findByFirstName_should_throw_exception_with_null_value() {
        Assertions.assertThrows(Exception.class, () -> studentDao.findByFirstName(null));
    }

    @Test
    public void findByFirstName_should_find_2_objects() {
        studentDao.removeAll();
        Student student1 = new Student("Anna", "Svensson1", LocalDate.parse("1990-07-12"), "email1@email.com");
        Student student2 = new Student("Anna", "Svensson1", LocalDate.parse("1990-07-12"), "email2@email.com");
        List<Student> expected = new ArrayList<>();
        expected.add(student1);
        expected.add(student2);
        studentDao.save(student1);
        studentDao.save(student2);
        List<Student> actual = studentDao.findByFirstName("Anna");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void update_should_update_object() {
        studentDao.removeAll();
        Student student1 = new Student("Anna", "Svensson1", LocalDate.parse("1990-07-12"), "email1@email.com");
        Student savedStudent = studentDao.save(student1);
        savedStudent.setFirstName("New_firstname");
        studentDao.update(savedStudent);
        Optional<Student> studentFromDb = studentDao.findById(savedStudent.getId());
        if (studentFromDb.isPresent()) {
            Assertions.assertEquals(savedStudent, studentFromDb.get());
            System.out.printf(savedStudent.toString());
        } else {
            //object not found
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void update_should_throw_exception_with_null_value() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            studentDao.update(null);
        });
    }
}
