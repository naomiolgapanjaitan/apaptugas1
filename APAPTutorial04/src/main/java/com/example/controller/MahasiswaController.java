package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.DTO;
import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;
import com.example.service.FakultasService;
import com.example.service.MahasiswaService;
import com.example.service.MahasiswaServiceDatabase;
import com.example.service.ProgramStudiService;
import com.example.service.UniversitasService;

import groovy.util.logging.Log;
import lombok.extern.slf4j.Slf4j;


@Slf4j

@Controller
public class MahasiswaController
{
    @Autowired
    MahasiswaService mahasiswaDAO;
    @Autowired
    ProgramStudiService prodi;
    @Autowired
    FakultasService fakultas;
    @Autowired
    UniversitasService universitas;
  

    @RequestMapping("/")
    public String index (Model model)
    {
    	  model.addAttribute ("title", "View All Student");
        return "index";
    }


    @RequestMapping("/mahasiswa/tambah")
    public String add (Model model)
    {
    	 List<ProgramStudiModel> prodis = prodi.selectAllProdi();;
    	model.addAttribute ("prodis", prodis);

        return "form-add";
    }

/*
    @RequestMapping("/mahasiswa/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        MahasiswaModel mahasiswa = new MahasiswaModel (npm, name, gpa);
        mahasiswaDAO.addMahasiswa (mahasiswa);

        return "success-add";
    }
*/

    @RequestMapping("/mahasiswa/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa (npm);
        ProgramStudiModel prod = prodi.selectProgramStudi(mahasiswa.getId_prodi());
        FakultasModel fk = fakultas.selectFakultas(prod.getId_fakultas());
        UniversitasModel unv = universitas.selectUniversitas(fk.getId_univ());
        DTO a = new DTO();
        a.setNama(mahasiswa.getNama());
        a.setNpm(mahasiswa.getNpm());
        a.setTanggal_lahir(mahasiswa.getTanggal_lahir());
        a.setTempat_lahir(mahasiswa.getTempat_lahir());
        a.setJenis_kelamin(mahasiswa.getJenis_kelamin());
        a.setAgama(mahasiswa.getAgama());
        a.setGolongan_darah(mahasiswa.getGolongan_darah());
        a.setTahun_masuk(mahasiswa.getTahun_masuk());
        a.setJalur_masuk(mahasiswa.getJalur_masuk());
        a.setStatus(mahasiswa.getStatus());
        a.setNama_prodi(prod.getNama_prodi());
        a.setNama_fakultas(fk.getNama_fakultas());
        a.setNama_universitas(unv.getNama_univ());
        if (mahasiswa != null) {
        	log.info ("masuk sini {}", a.getNama());
        	model.addAttribute ("a", a);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/mahasiswa/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
    	
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa (npm);
       // ProgramStudiModel prostud = prodi.selectProgramStudi(mahasiswa.getId());
        DTO a = new DTO();
        log.info ("mahasiswa nama {}", mahasiswa.getNama());
//        
        if (mahasiswa != null) {
       		return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }



    @RequestMapping("/mahasiswa/viewall")
    public String view (Model model)
    {
        List<MahasiswaModel> mahasiswas = mahasiswaDAO.selectAllMahasiswas ();
        model.addAttribute ("mahasiswas", mahasiswas);

        return "viewall";
    }


    @RequestMapping("/mahasiswa/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
        mahasiswaDAO.deleteMahasiswa (npm);

        return "delete";
    }

}
