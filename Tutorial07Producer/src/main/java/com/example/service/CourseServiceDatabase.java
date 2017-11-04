package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.CourseModel;

@Service
public class CourseServiceDatabase implements CourseService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
    public CourseModel selectCourse(String id_course)
    {
    	return studentMapper.selectCourse(id_course);
    }
	
	@Override
	public List<CourseModel> selectAllCourse()
	{
		return studentMapper.selectAllCourse();
	}
}
