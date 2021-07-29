package sg.edu.rp.c346.id20047223.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewTitle);
        ImageView ivNew = rowView.findViewById(R.id.imageView);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        RatingBar rating = rowView.findViewById(R.id.rbStars);

        // Obtain the Android Version information based on the position
        Song currentVersion = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getSongTitle());
        tvSinger.setText(currentVersion.getSingers());
        tvYear.setText(currentVersion.getSongYear() + "");

        rating.setRating(currentVersion.getSongRating());

        if(currentVersion.getSongYear() >= 2019){
            ivNew.setImageResource(R.drawable.newpng);
        } else {
            ivNew.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }
}