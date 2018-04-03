package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

public interface FakultasService
{
	FakultasModel selectFakultas (int id);
	List <FakultasModel> selectAllFakultas(int id_univ);
	List<FakultasModel> selectAllData();
	}
