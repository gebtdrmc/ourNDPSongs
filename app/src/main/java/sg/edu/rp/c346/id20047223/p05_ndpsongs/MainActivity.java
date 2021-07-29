package sg.edu.rp.c346.id20047223.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    TextView tvTitle;
    EditText etTitle;
    TextView tvSinger;
    EditText etSinger;
    TextView tvYear;
    EditText etYear;
    RadioGroup RGrating;
    Button btnInsert;
    Button btnShow;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onResume(){
        super.onResume();

        btnShow.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvSinger = findViewById(R.id.tvSinger);
        tvYear = findViewById(R.id.tvYear);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        RGrating = findViewById(R.id.rgrating);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titledata = etTitle.getText().toString();
                String singerdata = etSinger.getText().toString();
                String yeardata = etYear.getText().toString();
                int year = Integer.valueOf(yeardata);
                int stars = 1;
                switch (RGrating.getCheckedRadioButtonId()){
                    case R.id.rb1:
                        stars = 1;
                        break;
                    case R.id.rb2:
                        stars = 2;
                        break;
                    case R.id.rb3:
                        stars = 3;
                        break;
                    case R.id.rb4:
                        stars = 4;
                        break;
                    case R.id.rb5:
                        stars = 5;
                        break;
                }
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_title = dbh.insertSongTitle(titledata);
                long inserted_singer = dbh.insertSongSingers(singerdata);
                long inserted_year = dbh.insertSongYear(yeardata);

                if (inserted_title != -1 && inserted_singer != -1 && inserted_year != -1){
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Title Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song target = al.get(0);

                Intent i = new Intent(MainActivity.this,
                        ShowActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });
    }
}