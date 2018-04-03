package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

@Mapper
public interface UniversitasMapper
{
    @Select("select id, kode_univ , nama_univ   from universitas where id = #{id}")
   UniversitasModel selectUniversitas(@Param("id") int id);

    @Select("select id, kode_univ , nama_univ   from universitas ")
    List<UniversitasModel>selectAllUniv();

}
