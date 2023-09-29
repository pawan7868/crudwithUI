package com.student.StudentCrudAppWithUI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.student.StudentCrudAppWithUI.dao.StudentRepository;
import com.student.StudentCrudAppWithUI.entites.Student;


@Service
public class StudentService {

	@Autowired(required=true)
	private StudentRepository studentRepository;
	/*public Student createStudent(Student student) {
		return studentRepository.save(student);
		
		
	}*/
	public void createStudent(Student student) {
		System.out.println(student.getDob());
		 studentRepository.save(student);
		
		
	}
	public Optional<Student> findStudentById(Integer userId)  {
		
		return studentRepository.findById(userId);
		
	}
	public Iterable<Student> findAllStudents() {
		 return	studentRepository.findAll();
		}
	//sorting method service mehtod
	public Iterable<Student> getAllSortedStudents() {
		 //return	studentRepository.findAll(Sort.by("name"));
		return	studentRepository.findAll(Sort.by(Order.desc("name")));
		 //return	studentRepository.findAll(Sort.by("name").and(Sort.by("subject")));
		}
	
    //pagination
	public Page<Student> getStudentsWithPAgination(int offset,int size) {
		Page<Student> students=studentRepository.findAll(PageRequest.of(offset, size));
		
		return students;
	}
	//pagination with sorting
	public Page<Student> getStudentsWithPAginationAndSort(int offset,int size,String fieldNameForSorting) {
    Page<Student> students=studentRepository.findAll(PageRequest.of(offset, size).withSort(Sort.by(fieldNameForSorting)));
		return students;
	}
	
	public Student updateStudent(Student student) {

		Student std= studentRepository.findById(student.getId()).get();
		std.setName(student.getName());
		std.setSubject(student.getSubject());
		std.setDob(student.getDob());
		return studentRepository.save(std);
	}
	
	/*//update method for UI
	public String updateStudentData(Student student) {

		Student std= studentRepository.findById(student.getId()).get();
		std.setName(student.getName());
		std.setSubject(student.getSubject());
		std.setDob(student.getDob());
		return studentRepository.save(std);
	}*/
public void deleteStudentById(int studentId) {
		
		studentRepository.deleteById(studentId);
	}

public Iterable<Student> createStudents(Iterable<Student> allStudents) {
	return studentRepository.saveAll(allStudents);
	
}
}
