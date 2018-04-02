package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MahasiswaMapper;
import com.example.dao.ProgramStudiMapper;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService
{
	private static List<ProgramStudiModel> prodiList = new ArrayList<ProgramStudiModel>();

    @Autowired
    private ProgramStudiMapper programstudiMapper;


    @Override
    public  ProgramStudiModel selectProgramStudi (int id)
    {
        log.info ("select prostud with id {}", id);  
        return programstudiMapper.selectProgramStudi (id);
    }
    @Override
	public List<ProgramStudiModel> selectAllProdi(){
		
		return programstudiMapper.selectAllProdi();
	}
    

}
