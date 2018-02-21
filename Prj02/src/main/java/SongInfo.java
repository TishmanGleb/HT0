

public class SongInfo {
    private String songName;
    private String songDuration;
    private String songPath;


    public SongInfo(String songName, String songDuration, String songPath) {
        this.songName = songName;
        this.songDuration = songDuration;
        this.songPath = songPath;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public String getSongPath() {
        return songPath;
    }


}
