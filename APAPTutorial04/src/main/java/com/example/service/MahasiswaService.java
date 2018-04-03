package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

public interface MahasiswaService
{
    MahasiswaModel selectMahasiswa (String npm);
    MahasiswaModel selectMahasiswaById (int id);
    
    List<MahasiswaModel> selectAllMahasiswas ();
    void addMahasiswa (MahasiswaModel mahasiswa);
    void updateMahasiswa (MahasiswaModel mahasiswa);
    List<MahasiswaModel> selectAllMahasiswaByProdi(int id_prodi);
    int countMahasiswaByTahunAndProdi(int tahun, int idProdi);
	
    void deleteMahasiswa (String npm);
}
