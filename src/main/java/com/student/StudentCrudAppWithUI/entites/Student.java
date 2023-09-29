package com.student.StudentCrudAppWithUI.entites;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="Student")
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Integer id;
@Column(name="name")
@NotEmpty(message="name can't be blank, please enter the name")
String name;
@Column(name="subject")
@NotEmpty(message="subject can't be blank, please enter the subject")
String subject;
@NotEmpty(message="dob can't be blank, please enter the dob")

@Column(name="dob")
String dob;
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
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}
}
