package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.dao.MahasiswaMapper;
import com.example.dao.ProgramStudiMapper;
import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDatabase implements FakultasService
{
    @Autowired
    private FakultasMapper fakultasMapper;


    @Override
    public  FakultasModel selectFakultas (int id)
    {
        log.info ("select fakultas with id {}", id);  
        return fakultasMapper.selectFakultas(id);
    }
    @Override
  	public List<FakultasModel> selectAllFakultas(int id_univ){
  		
  		return fakultasMapper.selectAllFakultas(id_univ);
  	}
	public List<FakultasModel> selectAllData(){
  		
  		return fakultasMapper.selectAllData();
  	}



}
