package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProgramStudiMapper;
import com.example.dao.StudentMapper;
import com.example.model.ProgramStudiModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService
{
    @Autowired
    private ProgramStudiMapper programstudiMapper;


 
    @Override
    public List<ProgramStudiModel> selectAllProgramStudi ()
    {
        log.info ("select all students");
        return programstudiMapper.selectAllProgramStudi ();
    }


}
