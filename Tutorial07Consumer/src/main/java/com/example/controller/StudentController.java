package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.CourseService;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;
    CourseService courseDAO;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("page_title", "Add New Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa, Model model)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);
        model.addAttribute("page_title", "Add Sukses");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("page_title", "View Student by NPM");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("page_title", "Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	 model.addAttribute("page_title", "View Student by NPM");
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("page_title", "Not Found");
            return "not-found";
        }
    }
    
    @RequestMapping("/course/view/{id_course}")
    public String viewCourse (Model model, @PathVariable(value="id_course") String id_course)
    {
    	CourseModel course = studentDAO.selectCourse(id_course);
    	
    	if(course !=null) {
    		model.addAttribute("course", course);
    		 model.addAttribute("page_title", "View Course by ID Course");
    		return "view-course";
    	} else {
    		model.addAttribute("id_course", id_course);
    		 model.addAttribute("page_title", "Not Found");
    		return "not-found2";
    	}
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("page_title", "View All Student");

        return "viewall";
    }
    

    @RequestMapping("/course/viewall")
    public String viewallC(Model model)
    {
    	List<CourseModel> courses = studentDAO.selectAllCourse();
    	model.addAttribute("courses",courses);
    	model.addAttribute("page_title", "View All Courses");
    	return "viewallcourses";
    }
    


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent(npm);
        
        if(student == null) {
        	return "not-found";
        }
        studentDAO.deleteStudent (npm);
        model.addAttribute("page_title", "Delete Sukses");
        return "delete";
    }
    
    @RequestMapping("/student/update/{npm}")
	public String update(Model model, @PathVariable(value = "npm") String npm)
	{
		StudentModel student = studentDAO.selectStudent(npm);
		
		if(student == null) {
			return "not-found";
		}
		model.addAttribute("student", student);
		 model.addAttribute("page_title", "Update Mahasiswa");
		return "form-update";
	}
    
    
    @RequestMapping(value ="/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit(@ModelAttribute StudentModel student, Model model)
    {
    	
    	//StudentModel student = new StudentModel(npm, name, gpa);
    	studentDAO.updateStudent(student);
    	model.addAttribute("page_title", "Update Sukses");
    	return "success-update";
   
    }
    
    
    
}

