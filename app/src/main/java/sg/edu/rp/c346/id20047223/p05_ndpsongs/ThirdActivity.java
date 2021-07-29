package sg.edu.rp.c346.id20047223.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    TextView tvID, tvTitle;
    EditText etTitle;
    TextView tvSinger;
    EditText etSinger;
    TextView tvYear;
    EditText etYear;
    RatingBar rgRating;
    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    Song data;
    RadioButton r1, r2, r3, r4, r5;

    @Override
    protected void onResume(){
        super.onResume();

        btnCancel.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvID = findViewById(R.id.tvID);
        tvTitle = findViewById(R.id.tvTitle);
        tvSinger = findViewById(R.id.tvSinger);
        tvYear = findViewById(R.id.tvYear);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgRating = findViewById(R.id.rbStars);
        btnUpdate = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        r1 = findViewById(R.id.rb1);
        r2 = findViewById(R.id.rb2);
        r3 = findViewById(R.id.rb3);
        r4 = findViewById(R.id.rb4);
        r5 = findViewById(R.id.rb5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("song");

        tvID.setText("ID: " + data.getId());
        etTitle.setText(data.getSongTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getSongYear());
        switch (data.getSongRating()){
            case 1:
                r1.setChecked(true);
                break;
            case 2:
                r2.setChecked(true);
                break;
            case 3:
                r3.setChecked(true);
                break;
            case 4:
                r4.setChecked(true);
                break;
            case 5:
                r5.setChecked(true);
                break;
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setSongTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString().trim()));
                int rbID = rgRating.getNumStars();
                RadioButton rb = (RadioButton) findViewById(rbID);
                data.setRating(Integer.parseInt(rb.getText().toString()));

                dbh.updateNote(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
            }
        });
    }
}