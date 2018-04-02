package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper
{
    @Select("select id, npm, nama ,tempat_lahir, tanggal_lahir,agama,jenis_kelamin,golongan_darah,status,tahun_masuk,jalur_masuk,id_prodi from mahasiswa where npm = #{npm}")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);

    @Select("select npm, nama  from mahasiswa")
    List<MahasiswaModel> selectAllMahasiswas ();

    @Insert("INSERT INTO mahasiswa (npm, nama ) VALUES (#{npm}, #{nama})")
    void addMahasiswa (MahasiswaModel mahasiswa);
}
