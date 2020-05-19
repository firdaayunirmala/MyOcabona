package com.program.myocabona;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = DaftarActivity.class.getSimpleName();
    private String mSpinnerLabel = "";
    private Button btnDate, btnTime;
    private Button btnSignup;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        btnDate = (Button) findViewById(R.id.button_date);
        btnTime = (Button) findViewById(R.id.button_time);
        btnSignup = (Button) findViewById(R.id.btnSignup) ;

        Spinner spinner = (Spinner) findViewById(R.id.spinnerlogin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kota, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
        MasukActivity ();
    }

    public void MasukActivity() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MasukActivity.class);
                startActivity(i);
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.date_picker));
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.time_picker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Assign the concatenated strings to dateMessage.
        String dateMessage = (month_string + "/" +
                day_string + "/" + year_string);
        //Toast.makeText(this, getString(R.string.date) + dateMessage, Toast.LENGTH_SHORT).show();
        btnDate.setText(dateMessage);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + ":" + minute_string);
        // Toast.makeText(this, getString(R.string.time) + timeMessage,Toast.LENGTH_SHORT).show();
        btnTime.setText(timeMessage);
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