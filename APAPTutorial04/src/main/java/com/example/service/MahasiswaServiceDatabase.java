package com.example.service;

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


    @Override
    public List<MahasiswaModel> selectAllMahasiswas ()
    {
        log.info ("select all mahasiswas");
        return mahasiswaMapper.selectAllMahasiswas ();
    }


    @Override
    public void addMahasiswa (MahasiswaModel mahasiswa)
    {
        mahasiswaMapper.addMahasiswa (mahasiswa);
    }


    @Override
    public void deleteMahasiswa (String npm)
    {
    }

}
