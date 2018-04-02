package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO {
	private int id;
	private Long npm;
	private String nama;
	private String nama_lengkap;
    private String tempat_lahir;
    private Date tanggal_lahir;
    private String jenis_kelamin;
    private String agama;
    private String golongan_darah;
    private String tahun_masuk;
    private String status;
    private String jalur_masuk;
    private int id_prodi;
    private String nama_prodi;
    private String nama_fakultas;
    private String nama_universitas;
}
