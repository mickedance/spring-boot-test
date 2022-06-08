package com.example.springboottest.dao.impl;

import com.example.springboottest.dao.StudentDao;
import com.example.springboottest.entity.Student;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("student was null");
        entityManager.persist(student);
        return student;
    }

    @Override
    @Transactional
    public void remove(Student student) {
        findById(student.getId())
                .orElseThrow(() -> new ObjectNotFoundException("student not found", "Student"));
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public Optional<Student> findById(int id) {
        Student student = entityManager.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    @Override
    @Transactional
    public List<Student> findAll() {
        Query querySelectAllStudents = entityManager.createQuery("select s from Student s ");
        return querySelectAllStudents.getResultList();
    }

    @Override
    @Transactional
    public List<Student> findByFirstName(String name) throws IllegalArgumentException{
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("First name was null or empty");
        Query queryGetStudentsWithFirstName = entityManager.createQuery("select s from Student s  where s.firstName =?1");
        queryGetStudentsWithFirstName.setParameter(1, name);

        return queryGetStudentsWithFirstName.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        if (student == null) throw new IllegalArgumentException("Student was null");
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void removeAll() {
        List<Student> students = findAll();
        for(Student s: students){
            remove(s);
        }
    }
}
