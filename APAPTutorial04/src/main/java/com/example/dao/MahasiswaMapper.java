package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.MahasiswaModel;

@Mapper
public interface MahasiswaMapper
{
    @Select("select id, npm, nama ,tempat_lahir, tanggal_lahir,agama,jenis_kelamin,golongan_darah,status,tahun_masuk,jalur_masuk,id_prodi from mahasiswa where npm = #{npm}")
    MahasiswaModel selectMahasiswa (@Param("npm") String npm);
    
    @Select("select npm, nama ,tempat_lahir, tanggal_lahir,agama,jenis_kelamin,golongan_darah,status,tahun_masuk,jalur_masuk,id_prodi from mahasiswa where id = #{id}")
    MahasiswaModel selectMahasiswaById (@Param("id") int id);

    @Select("select npm, nama  from mahasiswa")
    List<MahasiswaModel> selectAllMahasiswas ();

    @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES ("
			+ "#{mahasiswa.npm},"
			+ "#{mahasiswa.nama},"
			+ "#{mahasiswa.tempat_lahir},"
			+ "#{mahasiswa.tanggal_lahir},"
			+ "#{mahasiswa.jenis_kelamin},"
			+ "#{mahasiswa.agama},"
			+ "#{mahasiswa.golongan_darah},"
			+ "#{mahasiswa.status},"
			+ "#{mahasiswa.tahun_masuk},"
			+ "#{mahasiswa.jalur_masuk},"
			+ "#{mahasiswa.id_prodi})")
	void addMahasiswa(@Param("mahasiswa") MahasiswaModel mahasiswa);  
    @Select("select id, npm, nama ,tempat_lahir, tanggal_lahir,agama,jenis_kelamin,golongan_darah,status,tahun_masuk,jalur_masuk,npm from mahasiswa where id_prodi = #{id_prodi}")
    MahasiswaModel selectMahasiswaByProdi (@Param("id_prodi") String id_prodi);
    @Select("SELECT COUNT(*) FROM mahasiswa WHERE tahun_masuk=#{thn} AND id_prodi=#{prodi}")
	int countMahasiswaByTahunAndProdi(@Param("thn") int tahun, @Param("prodi") int idProdi);
    @Update("UPDATE mahasiswa SET "
			+ "npm=#{mahasiswa.npm},"
			+ "nama=#{mahasiswa.nama},"
			+ "tempat_lahir=#{mahasiswa.tempat_lahir},"
			+ "tanggal_lahir=#{mahasiswa.tanggal_lahir},"
			+ "jenis_kelamin=#{mahasiswa.jenis_kelamin},"
			+ "agama=#{mahasiswa.agama},"
			+ "golongan_darah=#{mahasiswa.golongan_darah},"
			+ "status=#{mahasiswa.status},"
			+ "tahun_masuk=#{mahasiswa.tahun_masuk},"
			+ "jalur_masuk=#{mahasiswa.jalur_masuk},"
			+ "id_prodi=#{mahasiswa.id_prodi}"
			+ " WHERE id=#{mahasiswa.id}")
	void updateMahasiswa(@Param("mahasiswa") MahasiswaModel mahasiswa);

}
