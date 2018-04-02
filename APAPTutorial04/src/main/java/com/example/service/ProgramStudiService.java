package com.example.service;

import java.util.List;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;

import lombok.extern.slf4j.Slf4j;

public interface ProgramStudiService
{
	ProgramStudiModel selectProgramStudi (int id);
	List <ProgramStudiModel> selectAllProdi();
	
}
