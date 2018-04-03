package com.example.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService
{
    @Autowired
    private MahasiswaMapper mahasiswaMapper;


    @Override
    public MahasiswaModel selectMahasiswa (String npm)
    {
        log.info ("select mahasiswa with npm xxi {}", npm);
        return mahasiswaMapper.selectMahasiswa (npm);
    }
    public MahasiswaModel selectMahasiswaById (int id)
    {
        log.info ("select mahasiswa with npm xxi {}", id);
        return mahasiswaMapper.selectMahasiswaById (id);
    }
    @Override
	public int countMahasiswaByTahunAndProdi(int tahun, int idProdi) {
		return mahasiswaMapper.countMahasiswaByTahunAndProdi(tahun, idProdi);
	}

    @Override
    public List<MahasiswaModel> selectAllMahasiswas ()
    {
        log.info ("select all mahasiswas");
        return mahasiswaMapper.selectAllMahasiswas ();
    }
    public List<MahasiswaModel> selectAllMahasiswaByProdi (int id_prodi)
    {
        log.info ("select all mahasiswas");
        return mahasiswaMapper.selectAllMahasiswas ();
    }

    @Override
    public void addMahasiswa (MahasiswaModel mahasiswa)
    {
    	  mahasiswaMapper.addMahasiswa (mahasiswa);
    }
    public void updateMahasiswa (MahasiswaModel mahasiswa)
    {
        mahasiswaMapper.updateMahasiswa(mahasiswa);
    }

    @Override
    public void deleteMahasiswa (String npm)
    {
    }

}
