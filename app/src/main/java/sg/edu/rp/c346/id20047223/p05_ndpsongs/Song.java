package sg.edu.rp.c346.id20047223.p05_ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String songTitle;
    private String singers;
    private int year;
    private int rating;

    public Song(int id, String songContent, String singers, int year, int rating){
        this.id = id;
        this.songTitle = songContent;
        this.singers = singers;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {  return id;  }

    public String getSongTitle() { return songTitle; }

    public String getSingers() {  return singers;  }

    public int getSongYear() { return year; }

    public int getSongRating() { return rating; }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
    public void setSingers(String singers) {
        this.singers = singers;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        String starString = "";
        for(int i = 0; i< rating;i++){
            starString += "*";
        }
        return songTitle + "\n" + singers + "-" + year + "\n" + starString;  }
}
