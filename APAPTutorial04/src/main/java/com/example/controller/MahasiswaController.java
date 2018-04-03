package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    	MahasiswaModel mModel = null;
		model.addAttribute("mahasiswa", new MahasiswaModel());
		model.addAttribute("options_jk", mModel.JENIS_KELAMIN_OPTIONS);
		model.addAttribute("options_agama", mModel.AGAMA_OPTIONS);
		model.addAttribute("options_goldar", mModel.GOLONGAN_DARAH_OPTIONS);
		model.addAttribute("options_jalur_masuk", mModel.JALUR_MASUK_OPTIONS);
		model.addAttribute("linkSubmit", "/mahasiswa/tambah/submit");
		model.addAttribute("hideAlert", true);
        return "form-add";
    }
    @RequestMapping(value = "/mahasiswa/tambah/submit", method = RequestMethod.POST)
	public String addDataSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa) {
		mahasiswa.setNpm(getGenerateNPM(mahasiswa, "insert"));
		mahasiswa.setStatus("Aktif");
		mahasiswaDAO.addMahasiswa(mahasiswa);
		model.addAttribute("statMessage","Sukses!");
		model.addAttribute("message", String.format("Mahasiswa dengan NPM %s berhasil ditambahkan.", mahasiswa.getNpm()));
		return "success-add";
			}
	
    @RequestMapping("/kelulusan")
	public String viewPresentationGraduation(Model model, @RequestParam(value = "thn", required = false) String tahun,
			@RequestParam(value = "prodi", required = false) String idProdi) {
		model.addAttribute("closeOutput", true);
		ProgramStudiModel prodinew = new ProgramStudiModel();
		FakultasModel fakultasnew = new FakultasModel();
		UniversitasModel univnew = new UniversitasModel();
		int countMahasiswa = 0;
		
		if (tahun != null && idProdi != null) {
			int parseTahun = Integer.valueOf(tahun);
			int parseIdProdi = Integer.valueOf(idProdi);
			countMahasiswa = mahasiswaDAO.countMahasiswaByTahunAndProdi(parseTahun, parseIdProdi);
			
			prodinew =  prodi.selectProgramStudi(parseIdProdi);
			fakultasnew = fakultas.selectFakultas(prodi != null ? prodinew.getId_fakultas() : null);
			univnew = universitas.selectUniversitas(fakultas != null ? fakultasnew.getId_univ() : null);
			model.addAttribute("closeForm", true);
			model.addAttribute("closeOutput", false);
		}
		
		model.addAttribute("prodi", prodinew);
		model.addAttribute("fakultas", fakultasnew);
		model.addAttribute("universitas", univnew);
		model.addAttribute("totalPresentase", countMahasiswa);
		model.addAttribute("infoPresentase", String.format("%s dari 100 Mahasiswa Telas Lulus", countMahasiswa));
		return "kelulusan";
	}
    
    @RequestMapping("/mahasiswa/cari")
	public String viewDataByValue(Model model,
			@RequestParam(value = "univ", required = false) String idUniv,
			@RequestParam(value = "fakultas", required = false) String idFakultas,
			@RequestParam(value = "prodi", required = false) String idProdi) {
		
		List<UniversitasModel> listUniv = universitas.selectAllUniv();
		List<FakultasModel> listFakul = fakultas.selectAllData();
		List<ProgramStudiModel> listProdi = prodi.selectAllProdi();
		String namaProdiSelected = null;
		String namaFakultasSelected = null;
		model.addAttribute("optionsUniv", listUniv);
		
		if (idUniv != null) {
			List<FakultasModel> tListFakul = new ArrayList<>();
			for (FakultasModel fm : listFakul) {
				if (fm.getId_univ() == Integer.valueOf(idUniv))
					tListFakul.add(fm);
			}
			model.addAttribute("idUnivSelected", Integer.valueOf(idUniv));
			model.addAttribute("hideFakultas", false);
			model.addAttribute("optionsFakul", tListFakul);
			
			if (idFakultas != null) {
				List<ProgramStudiModel> tListProdi = new ArrayList<>();
				for (ProgramStudiModel psm : listProdi) {
					if (psm.getId_fakultas() == Integer.valueOf(idFakultas))
						tListProdi.add(psm);
				}
				model.addAttribute("idFakultasSelected", Integer.valueOf(idFakultas));
				model.addAttribute("hideProdi", false);
				model.addAttribute("optionsProdi", tListProdi);
				
				if (idProdi != null) {
					List<MahasiswaModel> listMahasiswa = mahasiswaDAO.selectAllMahasiswaByProdi(Integer.valueOf(idProdi));
					List<String[]> tmpData = new ArrayList<>();
					String namaFakultas = null;
					
					for(MahasiswaModel mhs : listMahasiswa) {
						String[] tmpS = new String[6];
						for (FakultasModel fk : listFakul) {
							if (fk.getId() == Integer.valueOf(idFakultas)) {
								namaFakultas = fk.getNama_fakultas();
								break;
							}
						}
						tmpS[0] = String.valueOf(mhs.getId());
						tmpS[1] = mhs.getNpm();
						tmpS[2] = mhs.getNama();
						tmpS[3] = namaFakultas;
						tmpS[4] = mhs.getTahun_masuk();
						tmpS[5] = mhs.getJalur_masuk();
						tmpData.add(tmpS);
					}
					
					for(FakultasModel fm : listFakul) {
						if(fm.getId() == Integer.valueOf(idFakultas)) {
							namaFakultasSelected = fm.getNama_fakultas();
							break;
						}
					}
					
					for(ProgramStudiModel psm : listProdi) {
						if(psm.getId() == Integer.valueOf(idProdi)) {
							namaProdiSelected = psm.getNama_prodi();
							break;
						}
					}
					
					model.addAttribute("msgFindMahasiswa", String.format("Lihat data Mahasiswa Program Studi %s Fakultas %s", namaProdiSelected, namaFakultasSelected));
					model.addAttribute("listMahasiswa", tmpData);
					
					return "viewAll";
				}
			}
		}
		return "carimahasiswa-univ";
	}
    @RequestMapping(value = "/mahasiswa/ubah/submit", method = RequestMethod.POST)
	public String editDataSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa) {
	mahasiswa.setNpm(getGenerateNPM(mahasiswa, "update"));
    	
		mahasiswa.setStatus("Aktif");
		mahasiswaDAO.updateMahasiswa(mahasiswa);
		return "redirect:/mahasiswa/ubah/" + mahasiswa.getNpm();
	}



    @RequestMapping("/mahasiswa/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
    	log.info ("masuk sini {}", npm);
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
    @RequestMapping("/mahasiswa/update/{npm}")
    public String update (Model model,@PathVariable(value = "npm") String npm)
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
    	 model.addAttribute ("mahasiswa", mahasiswa);

        return "form-update";
    }

    private String getGenerateNPM(MahasiswaModel mahasiswa, String mode) {
		ProgramStudiModel psm = prodi.selectProgramStudi(mahasiswa.getId_prodi());
		FakultasModel fm = fakultas.selectFakultas(psm.getId_fakultas());
		UniversitasModel um = universitas.selectUniversitas(fm.getId_univ());
		String tmpNPM = mahasiswa.generateNPM(
				mahasiswa.getTahun_masuk(), 
				String.valueOf(um.getKode_univ()),
				String.valueOf(mahasiswa.getId_prodi()), 
				mahasiswa.getJalur_masuk(), 
				mode.equals("insert") ? "001" : mahasiswa.getNpm().substring(9, 12));
		int i = 1;
		if (mode.equals("update"))
			return tmpNPM;
		while (mahasiswaDAO.selectMahasiswa(tmpNPM) != null) {
			tmpNPM = mahasiswa.generateNPM(
					mahasiswa.getTahun_masuk(), 
					String.valueOf(um.getKode_univ()),
					String.valueOf(mahasiswa.getId_prodi()), 
					mahasiswa.getJalur_masuk(), 
					String.format("00%s", i++));
		}
		return tmpNPM;
	}
    @RequestMapping("/mahasiswa/ubah/{npm}")
	public String editDataForm(Model model, @PathVariable("npm") String npm) {
		MahasiswaModel mModel = null;
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);

   	 List<ProgramStudiModel> prodis = prodi.selectAllProdi();
   	model.addAttribute ("prodis", prodis);
		model.addAttribute("mahasiswa", mahasiswa);
		model.addAttribute("options_jk", mModel.JENIS_KELAMIN_OPTIONS);
		model.addAttribute("options_agama", mModel.AGAMA_OPTIONS);
		model.addAttribute("options_goldar", mModel.GOLONGAN_DARAH_OPTIONS);
		model.addAttribute("options_jalur_masuk", mModel.JALUR_MASUK_OPTIONS);
		model.addAttribute("linkSubmit", "/mahasiswa/ubah/submit");

		return "form-add";
	}


}
