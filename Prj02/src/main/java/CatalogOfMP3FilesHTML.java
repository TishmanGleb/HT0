

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CatalogOfMP3FilesHTML {

    public void fillHTML(ArrayList<Artist> artists, String fileCreatePath) {


        StringBuilder pageHTML = new StringBuilder("<!DOCTYPE HTML>\n" +
                "<html>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tbody>\n"
        );

        for (Artist i : artists) {
            pageHTML.append("<tr>\n" +
                    "<td>" + i.getNameArtist().trim() + "</td>\n" +
                    "<td></td>\n" +
                    "<td></td>\n" +
                    "<td></td>\n" +
                    "<td></td>\n" +
                    "</tr>\n");//System.out.println(i.getNameArtist());
            for (Album j : i.getAlbums()) {
                pageHTML.append("<tr>\n" +
                        "<td></td>\n" +
                        "<td>" + j.getNameAlbum().trim() + "</td>\n" +
                        "<td></td>\n" +
                        "<td></td>\n" +
                        "<td></td>\n" +
                        "</tr>\n");//System.out.println("     " + j.getNameAlbum());
                for (SongInfo k : j.getSongInfo())
                    pageHTML.append("<tr>\n" +
                            "<td></td>\n" +
                            "<td></td>\n" +
                            "<td>" + k.getSongName().trim() + "</td>\n" +
                            "<td>" + k.getSongDuration() + "</td>\n" +
                            "<td><a href=\"" + k.getSongPath() + "\">Link on the song</a></td>\n" +
                            "</tr>\n");  //System.out.println("          " + k.getSongName() + "   " + k.getSongDuration() + "  " + k.getSongPath());
            }
        }


        pageHTML.append("</tbody>\n" +
                "</table>\n" +
                " </body>\n" +
                "</html>");


        File fileHTML = new File(fileCreatePath+"\\info.html");
        try {
            fileHTML.createNewFile();
            FileWriter fileWriter = new FileWriter(fileHTML);
            fileWriter.write(pageHTML.toString());

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Can't create HTML file in directory "+fileCreatePath);
        }

    }

}

