package vo.cvcompany.com.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtShow)
    TextView txtShow;
    @BindView(R.id.etDate)
    EditText etDate;
    @BindView(R.id.txtShow1)
    TextView txtShow1;

    @BindView(R.id.etTime1)
    EditText etTime1;
    @BindView(R.id.etTime2)
    EditText etTIme2;
    @BindView(R.id.txtResult)
    TextView txtResult;
    private Calendar calendar1;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showCalendar();
        showCalendar1();


    }

    private void showCalendar() {
        Calendar calendar  =Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        txtShow.setText(""+format.format( calendar.getTime()));

    }

    private void showCalendar1(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        txtShow1.setText(""+format.format(calendar.getTime()));
    }
    private void showDatePickerDialog(){
        final Calendar calendar =  Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day  =calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(year, month, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                etDate.setText(""+format.format( calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();
    }

    @OnClick(R.id.etDate)
    public void etDate(){
        showDatePickerDialog();
    }

    @OnClick(R.id.etTime1)
    public void etTime1(){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day  = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                etTime1.setText(""+format.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.etTime2)
    public void etTIme2(){
        calendar1 = Calendar.getInstance();
        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        int day  = calendar1.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    etTIme2.setText(""+simpleDateFormat.format(calendar1.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnProcess)
    public void btnProcess(){
        String time1 = etTime1.getText().toString().trim();
        String time2 = etTIme2.getText().toString().trim();
        if(time1.length()==0 && time2.length()==0){
            etTime1.setBackgroundColor(Color.RED);
            etTIme2.setBackgroundColor(Color.RED);
            Toast.makeText(this, ""+"Time1 vs Time 2 are null", Toast.LENGTH_SHORT).show();
            return;
        }else if(time1.length()==0 && time2.length()>0){

                etTime1.setBackgroundColor(Color.RED);
                Toast.makeText(this, ""+ "Time 1 is null", Toast.LENGTH_SHORT).show();
            return;

        }else if(etTime1.length()>0 && etTIme2.length()==0){
            etTIme2.setBackgroundColor(Color.RED);
            Toast.makeText(this, "Time 2 is null", Toast.LENGTH_SHORT).show();
            return;
        }
        long results = (calendar1.getTimeInMillis()-calendar.getTimeInMillis())/(1000*3600*24);
        txtResult.setText(""+results);

    }

    @OnTextChanged(R.id.etTime1)
    public void onTextCHangedTIme1(){
        etTime1.setBackgroundColor(Color.TRANSPARENT);
    }
    @OnTextChanged(R.id.etTime2)
    public void onTextCHangedTIme2(){
        etTIme2.setBackgroundColor(Color.TRANSPARENT);
    }



}
