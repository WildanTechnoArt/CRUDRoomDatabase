package com.wildan.crudroomdatabase.data;

//========== WILDAN TECHNO ART ==========//
//======= Cianjur Apps Developer ========//

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.wildan.crudroomdatabase.model.Mahasiswa;

@Dao
public interface MahasiswaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tMahasiswa")
    Mahasiswa[] readDataMahasiswa();

    @Update
    int updateMahasiswa(Mahasiswa mahasiswa);

    @Delete
    void deleteMahasiswa(Mahasiswa mahasiswa);

    @Query("SELECT * FROM tMahasiswa WHERE nim_mahasiswa = :nim LIMIT 1")
    Mahasiswa selectDetailMahasiswa(String nim);
}