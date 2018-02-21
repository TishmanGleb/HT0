

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;



public class Mp3Reader {

    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<File> allMP3Files = new ArrayList<>();
    private Logger logger = LogManager.getRootLogger();


    public static void main(String[] args) throws IOException {

        HashSet<String> pathOfCatalogs = new HashSet<>();

        if (args.length == 0) {
            System.out.println("Parameters not found! Please enter paths to directories.");
        }
        else {
            for (int i = 0; i < (args.length); i++) {
                pathOfCatalogs.add(args[i]);
            }


            CatalogOfMP3FilesHTML catMP3 = new CatalogOfMP3FilesHTML();

            for (String i : pathOfCatalogs) {
                Mp3Reader mp3Reader = new Mp3Reader();
                mp3Reader.path(new File(i)); //initialize and fill artists arraylist
                catMP3.fillHTML(mp3Reader.artists, i); //create HTML file with information of all mp3 in path
                mp3Reader.duplicatesByCheckSum(); //find files with same check sum and write path infomation in dupLog1.log and dupLog2.log
                mp3Reader.sameMP3ExeptCheckSum();
            }
        }
    }

    public String getDuration(File file) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {

        AudioFile audioFile = AudioFileIO.read(file);
        int duration = audioFile.getAudioHeader().getTrackLength();
        return duration % 60 < 10 ? String.valueOf(duration / 60) + ":0" + String.valueOf(duration % 60) : String.valueOf(duration / 60) + ":" + String.valueOf(duration % 60);

    }


    public void path(File f) {
        if (!f.exists()) {
            System.out.println("Path doesn't exist: " + f.getPath());
            return;
        }
        File[] files = f.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].canRead()) {
                if (files[i].isFile()) {
                    int dot = files[i].getName().lastIndexOf(".");
                    if (dot > 0)
                        if (files[i].getName().substring(dot).equals(".mp3")) {
                            getInfoMp3WithSimpleLibFile(files[i]);
                            allMP3Files.add(files[i]);
                        }
                } else {
                    path(files[i]);
                }
            }
        }
    }

    //Find artist, album, sonng name and duration of file
    // write it to artists arraylist
    public void getInfoMp3WithSimpleLibFile(File song) {
        try {

            FileInputStream file = new FileInputStream(song);
            int size = (int) song.length();
            file.skip(size - 128);
            byte[] last128 = new byte[128];
            file.read(last128);

            String id3 = new String(last128);
            String tag = id3.substring(0, 3);

            if (tag.equals("TAG")) {

                String title = id3.substring(3, 32);
                String currentArtist = id3.substring(33, 62);
                String currentAlbum = id3.substring(63, 91);
                SongInfo songInfo = new SongInfo(title, getDuration(song), song.getPath());

                addArtist(currentArtist);
                artists.get(getIdArtist(currentArtist)).addAlbum(currentAlbum, songInfo);
            }

            file.close();

        } catch (Exception e) {

            System.out.println("Error - " + e.toString());
        }
    }

    public boolean checkUniqueArtistName(String artistName) {
        for (Artist i : artists)
            if (i.getNameArtist().equals(artistName)) return false;
        return true;
    }

    public int getIdArtist(String artistName) {
        for (int i = 0; i < artists.size(); i++) {
            if (artists.get(i).getNameArtist().equals(artistName)) {
                return i;
            }
        }
        return 0;
    }

    public void addArtist(String artistName) {
        if (checkUniqueArtistName(artistName)) artists.add(new Artist(artistName));
    }

    public String findCheckSum(File file) throws IOException {
        FileInputStream findMD5 = new FileInputStream(file);
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(findMD5);
    }

    //Find files for which the CheckSum is the same and write them path to log
    public void duplicatesByCheckSum() throws IOException {
        for (int i = 0; i < allMP3Files.size() - 1; i++) {
            for (int j = i + 1; j < allMP3Files.size(); j++) {
                if (findCheckSum(allMP3Files.get(i)).equals(findCheckSum(allMP3Files.get(j)))) {
                    logger.error(allMP3Files.get(i).getPath());
                    logger.fatal(allMP3Files.get(j).getPath());
                }
            }
        }
    }

    public void sameMP3ExeptCheckSum() {
        for (Artist i : artists) {
            for (Album j : i.getAlbums()) {
                for (int k = 0; k < j.getSongInfo().size() - 1; k++) {
                    for (int l = k; l < j.getSongInfo().size(); l++) {
                        if(j.getSongInfo().get(k).equals(j.getSongInfo().get(l))){
                            File fileMain = new File(j.getSongInfo().get(k).getSongPath());
                            File checkToFileMain = new File(j.getSongInfo().get(l).getSongPath());

                            try {
                                if(!findCheckSum(fileMain).equals(findCheckSum(checkToFileMain))){
                                    logger.error(i.getNameArtist()+"  "+j.getNameAlbum()+"  "+j.getSongInfo().get(k).getSongName()+"\n"+
                                    fileMain.getPath());
                                    logger.fatal(i.getNameArtist()+"  "+j.getNameAlbum()+"  "+j.getSongInfo().get(l).getSongName()+"\n"+
                                            fileMain.getPath());
                                }
                            } catch (IOException e) {

                            }

                        }
                    }
                }
            }
        }
    }


}
