package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.ProgramStudiModel;
import com.example.model.StudentModel;
import com.example.service.ProgramStudiService;
import com.example.service.StudentService;

@Controller
public class MahasiswaController
{
    @Autowired
    StudentService studentDAO;
   

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }
    @RequestMapping("/student/home")
    public String home ()
    {
        return "home";
    }

  
    /*
    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa);
        studentDAO.addStudent (student);

        return "success-add";
    }
*/

    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
   //     log.info ("npmnya",students.);
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	String res="delete";
    	StudentModel student = studentDAO.selectStudent (npm);
    	if (student!= null) {
        studentDAO.deleteStudent (npm);
    	}
    	else {
    		res="not-found";
    	}
        return res;
    }
    @RequestMapping("/student/update/{npm}")
    public String update (Model model,@PathVariable(value = "npm") String npm)
    {
    	 StudentModel student = studentDAO.selectStudent (npm);
    
    	        model.addAttribute ("student", student);
    	  
        return "form-update";
    }
/*
    @RequestMapping(value="/student/update/submit", method =RequestMethod.POST)
   public String updateSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa) {
   
    	String res="success-update";
    	  StudentModel student = new StudentModel (npm, name, gpa);
          studentDAO.updateStudent (student);

        return res;
    }
    
    @RequestMapping(value="/student/tambah/submit", method =RequestMethod.POST)
    public String updateSubmit (StudentModel model) {
   
    	String res="success-update";
    	StudentModel student = new StudentModel (model.getId(), model.ge, model.getGpa());
          studentDAO.updateStudent (student);

        return res;
}*/
}
