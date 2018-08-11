package com.wildan.crudroomdatabase.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.wildan.crudroomdatabase.R;
import com.wildan.crudroomdatabase.model.Mahasiswa;

public class DetailDataActivity extends AppCompatActivity {

    private EditText getNIM, getName, getJurusan, getTanggalLahir, getJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getNIM = findViewById(R.id.myNIM);
        getName = findViewById(R.id.myName);
        getJurusan = findViewById(R.id.myJurusan);
        getTanggalLahir = findViewById(R.id.myTanggalLahir);
        getJenisKelamin = findViewById(R.id.myJenisKelamin);

        getDetailData();
    }

    //Mendapatkan data yang akan ditampilkan secara detail
    private void getDetailData(){
        Mahasiswa mahasiswa = (Mahasiswa)getIntent().getSerializableExtra("detail");

        //Menampilkan data Mahasiswa pada activity
        if(mahasiswa != null){
            getNIM.setText(mahasiswa.getNim());
            getName.setText(mahasiswa.getNama());
            getJurusan.setText(mahasiswa.getJurusan());
            getTanggalLahir.setText(mahasiswa.getTanggalLahir());
            getJenisKelamin.setText(mahasiswa.getJenisKelamin());
        }
    }
}