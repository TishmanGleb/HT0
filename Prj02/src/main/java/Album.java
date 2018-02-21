
import java.util.ArrayList;

public class Album {
    private String nameAlbum;
    private ArrayList<SongInfo> songInfo = new ArrayList<>();

    public Album(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public ArrayList<SongInfo> getSongInfo() {
        return songInfo;
    }
}
