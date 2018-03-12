package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.ProgramStudiModel;
import com.example.model.StudentModel;
import com.example.service.ProgramStudiService;
import com.example.service.StudentService;
import com.example.service.StudentServiceDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Controller
public class ProgramStudiController
{

 @Autowired
    ProgramStudiService prodiDAO;


    @RequestMapping("/mahasiswa/tambah")
    public String tambahmahasiswa (Model model)
    {
    	List<ProgramStudiModel> prodi =prodiDAO.selectAllProgramStudi();
    	 log.info ("prodi"+prodi.size());
        model.addAttribute ("listprodi", prodi);
    	     
        return "addMahasiswa";
    }
    
   
}
