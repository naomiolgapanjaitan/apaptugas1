package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

@Mapper
public interface FakultasMapper
{
    @Select("select id, nama_fakultas , kode_fakultas, id_univ  from fakultas where id = #{id}")
    FakultasModel selectFakultas (@Param("id") int id);
    @Select("select id, nama_fakultas , kode_fakultas, id_univ  from fakultas where id_univ = #{id}")
    List <FakultasModel> selectAllFakultas (@Param("id_univ") int id_univ);

    @Select("select id, nama_fakultas , kode_fakultas, id_univ  from fakultas ")
    List <FakultasModel> selectAllData ();

}
