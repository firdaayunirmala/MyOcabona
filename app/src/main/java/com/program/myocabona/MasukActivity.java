package com.program.myocabona;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MasukActivity extends AppCompatActivity {
    private Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        bindView();
        MenuActivity ();
    }

    private void bindView() {
        btnSignin = findViewById(R.id.btnSignin);
    }

    public void MenuActivity () {
        btnSignin.setOnClickListener(view -> {
            Intent intent =new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
        });
    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin akan keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Jika ditekan ya, maka tutup aplikasi
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //jika ditekan no, maka kembali ke aplikasi
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
