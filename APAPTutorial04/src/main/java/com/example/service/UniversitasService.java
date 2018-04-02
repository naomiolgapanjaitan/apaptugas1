package com.example.service;

import java.util.List;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

public interface UniversitasService
{
	UniversitasModel selectUniversitas (int id);
}
