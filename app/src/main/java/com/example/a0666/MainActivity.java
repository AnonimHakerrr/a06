package com.example.a0666;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private Button btnCalendar;
    private TextView tvDate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalendar = findViewById(R.id.btnCalendar);
        tvDate = findViewById(R.id.tvDate);
        btnDelete = findViewById(R.id.btnDelete);

        tvDate.setText(
                getResources().getString(R.string.hello) +
                        " " +
                        getResources().getString(R.string.world)
        );

        btnCalendar.setOnClickListener(this::showCalendar);
        btnDelete.setOnClickListener(this::deleteContact);

    }

    private void deleteContact(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Вы уверены, что хотите выйти?")
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish(); // Закрыть приложение
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Продолжить работу в приложении
                        }
                    });
            builder.create().show();

    }

    private void showCalendar(View v) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePickerDialog.OnDateSetListener) (view, year, month, dayOfMonth) -> {
                    //tvDate.setText(year + " " + (month + 1) + " " + dayOfMonth);

//                        String y = String.valueOf(year);
//                        month++;
//                        String m = month < 10 ? "0" + month : String.valueOf(month);
//                        String d = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
//                        String date = d + "/" + m + "/" + y;
//                        tvDate.setText(date);

                    Date d = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    String date = new SimpleDateFormat("dd:MM:yyyy").format(d);
                    tvDate.setText(date);

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(this, "Отклонено", Toast.LENGTH_SHORT).show();
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                Toast.makeText(this, "Подумайте до заватра", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_save:
                save();
                return true;
            case R.id.item_exit:
                exit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void exit() {
        Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void save() {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

    }


}