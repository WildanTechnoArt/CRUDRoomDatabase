package com.wildan.crudroomdatabase.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wildan.crudroomdatabase.R;
import com.wildan.crudroomdatabase.data.factory.AppDatabase;
import com.wildan.crudroomdatabase.model.Mahasiswa;
import com.wildan.crudroomdatabase.ui.DetailDataActivity;
import com.wildan.crudroomdatabase.ui.EditActivity;

import java.util.ArrayList;

public class RecyclerMahasiswaAdapter extends RecyclerView.Adapter<RecyclerMahasiswaAdapter.ViewHolder> {

    //Deklarasi Variable
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerMahasiswaAdapter(ArrayList<Mahasiswa> daftarMahasiswa, Context context) {

        //Inisialisasi data yang akan digunakan
        this.daftarMahasiswa = daftarMahasiswa;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbMahasiswa").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //Deklarasi View yang akan digunakan
        private TextView Nim, Nama;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);
            Nim = itemView.findViewById(R.id.nim);
            Nama = itemView.findViewById(R.id.nama);
            item = itemView.findViewById(R.id.cvMain);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Deklarasi Variable untuk mendapatkan data dari Database melalui Array
        final String getNIM = daftarMahasiswa.get(position).getNim();
        final String getNama = daftarMahasiswa.get(position).getNama();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        holder.Nim.setText(getNIM);
        holder.Nama.setText(getNama);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa mahasiswa = appDatabase.mahasiswaDAO()
                        .selectDetailMahasiswa(daftarMahasiswa.get(position).getNim());
                context.startActivity(new Intent(context, DetailDataActivity.class).putExtra("detail", mahasiswa));
            }
        });

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("Pilih Aksi")
                        .setItems(menuPilihan, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        /*
                                         Menjalankan Perintah Edit Data
                                         Menggunakan Bundle untuk mengambil data yang akan Diedit
                                         */
                                        onEditData(position, context);
                                        break;

                                    case 1:
                                        onDeleteData(position);
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }

    //Menghapus Data dari Room Database yang dipilih oleh user
    private void onDeleteData(int position){
        appDatabase.mahasiswaDAO().deleteMahasiswa(daftarMahasiswa.get(position));
        daftarMahasiswa.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarMahasiswa.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    //Mengirim Data yang akan diedit dari ArrayList berdasarkan posisi item pada RecyclerView
    private void onEditData(int position, Context context){
        context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarMahasiswa.get(position)));
        ((Activity)context).finish();
    }

    @Override
    public int getItemCount() {
        //Menghitung data / ukuran dari Array
        return daftarMahasiswa.size();
    }

}