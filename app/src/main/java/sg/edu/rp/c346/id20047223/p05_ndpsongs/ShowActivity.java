package sg.edu.rp.c346.id20047223.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button fivestars;
    ListView lv;
    ArrayList<Song> al;
    CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        fivestars = findViewById(R.id.btnFive);
        lv = findViewById(R.id.lvSongs);

        al = new ArrayList<Song>();
        ca = new CustomAdapter(this,
                R.layout.row, al);
        lv.setAdapter(ca);

        fivestars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                dbh.getSongByStars();
                dbh.close();
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song song = al.get(position);
                Intent i = new Intent(ShowActivity.this,
                        ThirdActivity.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });
    }
}