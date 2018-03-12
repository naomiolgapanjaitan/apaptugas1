package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.ProgramStudiModel;
import com.example.model.StudentModel;

@Mapper
public interface ProgramStudiMapper
{

    @Select("select kode_prodi, nama_prodi,id_fakultas from program_studi")
    List<ProgramStudiModel> selectAllProgramStudi ();

}
