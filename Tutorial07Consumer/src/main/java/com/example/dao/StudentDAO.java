package com.example.dao;

import java.util.List;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

public interface StudentDAO {
	
	StudentModel selectStudent(String npm);
	List<StudentModel> selectAllStudents();
	CourseModel selectCourse(String id_course);
	List<CourseModel> selectAllCourse();
}
