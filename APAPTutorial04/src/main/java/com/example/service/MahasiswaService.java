package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

public interface MahasiswaService
{
    MahasiswaModel selectMahasiswa (String npm);
   
    List<MahasiswaModel> selectAllMahasiswas ();


    void addMahasiswa (MahasiswaModel mahasiswa);


    void deleteMahasiswa (String npm);
}
