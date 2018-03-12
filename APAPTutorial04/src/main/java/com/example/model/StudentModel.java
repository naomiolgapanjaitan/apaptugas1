package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel
{
	 
   
    private String nama_lengkap;
    private String tempat_lahir;
    private Date tanggal_lahir;
    private String jenis_kelamin;
    private String agama;
    private String gol_darah;
    private String tahun_masuk;
    private int id_prodi;
   
    public  class NPMBuilder {
    	 private String npm;
        public NPMBuilder npmGenerator(String npm ) {
            this.npm ="";
            String thnmasuk= tahun_masuk.substring(2);
            return this;
        }
    }
    
    
}
