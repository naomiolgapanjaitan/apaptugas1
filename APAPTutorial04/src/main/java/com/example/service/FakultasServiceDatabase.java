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



}
