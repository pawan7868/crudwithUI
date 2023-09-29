package com.student.StudentCrudAppWithUI.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.student.StudentCrudAppWithUI.entites.Student;
import com.student.StudentCrudAppWithUI.exception.StudentDataAccessException;
import com.student.StudentCrudAppWithUI.exception.StudentRecordDeleteException;
import com.student.StudentCrudAppWithUI.service.StudentService;


@Controller

public class StudentController {
	
	
	@Autowired(required=true)
	private StudentService studentService;
	
	@GetMapping("/")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "index";
    }
	@GetMapping("/getAllStudents")
    public String viewStudentList(Model model) {
        model.addAttribute("studentsList", studentService.findAllStudents());
        return "getAllStudents";
    }
	
	
	//@RequestMapping(path = "create/student",method = RequestMethod.POST)
		/*@PostMapping(path = "/create/student")
	public ResponseEntity<Student> createStudent(@Valid  @RequestBody Student student) {
			Student std= studentService.createStudent(student);
			return ResponseEntity.ok().body(std);
	}*/
		 @PostMapping("/create/student")
		    public String createStudents(@ModelAttribute("student") Student student) {
		        // save employee to database
			 
			 studentService.createStudent(student);
		        return "redirect:/";
		    }
		
		@GetMapping(path = "id/{id}")
		public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) throws StudentDataAccessException {
			Student student = studentService.findStudentById(id).orElseThrow(()->new StudentDataAccessException("User not found with ID = "+id));
			return ResponseEntity.ok().body(student);
		}	

		@GetMapping(path = "/allStudents")
		public Iterable<Student> getAllStudents() {
			return studentService.findAllStudents();
		}
		//sorting method call
		@GetMapping(path = "/allSortedStudents")
		public Iterable<Student> getAllSortedStudents( ) {
			return studentService.getAllSortedStudents();
		}
		
		//pagination
		
		@GetMapping(path = "/pagination/{offset}/{size}")
		public Page<Student> getStudentsWithPAgination(@PathVariable int offset,@PathVariable int size) {
			Page<Student> prouctWithPagination=	studentService.getStudentsWithPAgination(offset, size);
		
			return  prouctWithPagination;
		
		}
		
		@GetMapping(path = "/paginationWithSorting/{offset}/{size}/{fieldNameForSorting}")
		public Page<Student> getStudentsWithPAgination(@PathVariable int offset,@PathVariable int size,@PathVariable String fieldNameForSorting ) {
			
			Page<Student> prouctWithPagination=	studentService.getStudentsWithPAginationAndSort(offset, size,fieldNameForSorting);
			return  prouctWithPagination;
		
		}
		@PutMapping(path = "/update")
		public Student updateStudent(@RequestBody Student student) {
			return studentService.updateStudent(student);
		}
		//update method for UI
		@GetMapping(path = "/updateStudent/{id}")
		public String updateStudentData(@PathVariable(value = "id") int id,Model model) {
			// get Student from the service
			Student student1 = studentService.findStudentById(id).get();

	        // set employee as a model attribute to pre-populate the form
	        model.addAttribute("student", student1);
	        return "updateStudent";
		}
		//delete method for json/postman
		/*@DeleteMapping(path = "delete/id/{id}")
		public ResponseEntity<Student> deleteStudentById(@PathVariable("id") Integer studentId)throws StudentRecordDeleteException {
			Student student=studentService.findStudentById(studentId).orElseThrow(()->new StudentRecordDeleteException("User not found with ID = "+studentId));
			studentService.deleteStudentById(studentId);
			return ResponseEntity.ok().body(student);
		}*/
		//delete method for UI
		@GetMapping(path = "delete/id/{id}")
		public String deleteStudentById(@PathVariable("id") Integer studentId)throws StudentRecordDeleteException {
			Student student=studentService.findStudentById(studentId).orElseThrow(()->new StudentRecordDeleteException("User not found with ID = "+studentId));
			studentService.deleteStudentById(studentId);
			 return "redirect:/getAllStudents";
		}
		
		@PostMapping(path = "multiStudents/create")
		public Iterable<Student> createStudents(@RequestBody  Iterable<Student> students) {
			return studentService.createStudents(students);
		}
}
