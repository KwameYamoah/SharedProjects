package HelperClasses;


import java.io.File;
import java.util.ArrayList;

//Finds all files to be read
public class FileSearcher {
    String directory;                               //Directory where files are located
    ArrayList<File> fileList = new ArrayList();   //List of files to be read
    public FileSearcher(String dir){
        this.directory = dir;

    }



    private int scoreFile(File file){
        String[] readfile = readFile(file);


        return 0;
    }

    // Method to read pdf file and extract contents as text
    private String[] readFile(File file) {

        return null;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setFileList(ArrayList<File> fileList) {
        this.fileList = fileList;
    }
}
