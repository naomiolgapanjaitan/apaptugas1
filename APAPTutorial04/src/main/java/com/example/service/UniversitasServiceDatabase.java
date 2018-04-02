package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FakultasMapper;
import com.example.dao.MahasiswaMapper;
import com.example.dao.ProgramStudiMapper;
import com.example.dao.UniversitasMapper;
import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UniversitasServiceDatabase implements UniversitasService
{
    @Autowired
    private UniversitasMapper universitasMapper;


    @Override
    public  UniversitasModel selectUniversitas (int id)
    {
       
        return universitasMapper.selectUniversitas(id);
    }



}
