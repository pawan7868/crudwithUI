package com.student.StudentCrudAppWithUI.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.student.StudentCrudAppWithUI.entites.Student;


public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

}
