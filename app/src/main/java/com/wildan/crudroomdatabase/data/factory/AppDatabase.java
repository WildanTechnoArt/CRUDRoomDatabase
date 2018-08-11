package com.wildan.crudroomdatabase.data.factory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wildan.crudroomdatabase.data.MahasiswaDAO;
import com.wildan.crudroomdatabase.model.Mahasiswa;

/*
 * Membuat Class Database
 * Entity yang digunakan adalah Mahasiswa.class
 * Version = 1
 */
@Database(entities = {Mahasiswa.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    //Untuk mengakses Database menggunakan method abstract
    public abstract MahasiswaDAO mahasiswaDAO();
}