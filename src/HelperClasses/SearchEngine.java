package HelperClasses;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

//Finds all files to be read
public class SearchEngine {
    ArrayList<String> rootDirectories = new ArrayList();    //List of all root directories chosen by user
    ArrayList<File> fileList = new ArrayList();             //List of files to be read
    ArrayList<String> directoryList = new ArrayList();      //List of directories from root
    String searchTerm;                                      //Search term for search engine

    /////////////////////////
    //////FOR TESTING////////
    String rootDir;
    /////////////////////////

    public SearchEngine(String rootDirectories, String searchTerm){
//        this.rootDirectories = rootDirectories;
        this.searchTerm = searchTerm;
        this.rootDir = rootDirectories;

    }



    private int scoreFile(File file){
        String[] readfile = readFile(file);


        return 0;
    }

    // Method to read pdf file and extract contents as text
    private String[] readFile(File file) {

        return null;
    }


    //TODO should return ArrayList<String>
    public void getAllDirectories(){
        File folder = new File(this.rootDir);
        File[] listOfFiles = folder.listFiles();

        for(int i=0;i<listOfFiles.length;i++){
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }


    }

        //Get all files in every subfolder from root directory
//    public ArrayList<String> getFilesFromRootDir(){
//
//    }

        //Get all files from given directory
//    public ArrayList<String> getFilesFromDir(){
//
//    }

    public void setFileList(ArrayList<File> fileList) {
        this.fileList = fileList;
    }
}
