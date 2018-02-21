

import java.util.ArrayList;

public class Artist {
    private String nameArtist;
    private ArrayList<Album> albums = new ArrayList<>();

    public Artist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public boolean checkUniqueAlbumName(String albumName) {
        for (Album i : albums)
            if (i.getNameAlbum().equals(albumName)) return false;
        return true;
    }

    public int getIdAlbum(String albumName) {

        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getNameAlbum().equals(albumName)) return i;
        }
        return 0;
    }

    public void addAlbum(String albumName, SongInfo songInfo) {
        if (checkUniqueAlbumName(albumName)) {
            Album album = new Album(albumName);
            album.getSongInfo().add(songInfo);
            albums.add(album);
        } else
            albums.get(getIdAlbum(albumName)).getSongInfo().add(songInfo);
    }
}
