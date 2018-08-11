package com.wildan.crudroomdatabase.ui;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wildan.crudroomdatabase.R;
import com.wildan.crudroomdatabase.adapter.RecyclerMahasiswaAdapter;
import com.wildan.crudroomdatabase.data.factory.AppDatabase;
import com.wildan.crudroomdatabase.model.Mahasiswa;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadDataActivity extends AppCompatActivity {

    //Deklarasi Variable
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Mahasiswa> daftarMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        getSupportActionBar().setTitle("Daftar Mahasiswa");

        //Inisialisasi ArrayList
        daftarMahasiswa = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbMahasiswa").allowMainThreadQueries().build();

        /*
         * Mengambil data Mahasiswa dari Database
         * lalu memasukannya ke kedalam ArrayList (daftarMahasiswa)
         */
        daftarMahasiswa.addAll(Arrays.asList(database.mahasiswaDAO().readDataMahasiswa()));

        //Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.dataItem);

        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RecyclerMahasiswaAdapter(daftarMahasiswa, ReadDataActivity.this);
        recyclerView.setAdapter(adapter);
    }
}