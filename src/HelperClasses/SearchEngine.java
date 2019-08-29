package HelperClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;



//Finds all files to be read

/** Finds all files to be searched
 *      Returns the results
 */
public class SearchEngine {
    ArrayList<String> rootDirectories = new ArrayList();    //List of all root directories chosen by user
    ArrayList<File> fileList = new ArrayList();             //List of files to be read
    ArrayList<String> directoryList = new ArrayList();      //List of directories accessed
    String searchTerm = "";                                 //Search term for search engine
    HashMap<HashMap<String,String>,Float> results;          //HashMap containing [[File_Name, File_Path],Score]

    /////////////////////////
    //////FOR TESTING////////
    String rootDir;
    /////////////////////////

    //Constructor
    public SearchEngine(String rootDirectories, String searchTerm){
//        this.rootDirectories = rootDirectories;
        this.rootDir = rootDirectories;
        this.searchTerm = searchTerm;

    }


    /**
     * Sets a new search term before searching files using new search term
     * @param newSearchTerm
     */
    public void search(String newSearchTerm){
        setSearchTerm(newSearchTerm);
        search();
    }

    /**
     * Searches files using the search term
     */
    public void search(){
        System.out.println("Searching for: "+this.searchTerm);

    }









    /** getAllDirectories: Get all directories within current directory (same level)
     *
     * @return
     */
    public ArrayList<String> getAllDirectories(){
         ArrayList<String> directories = new ArrayList();
         File folder = new File(this.rootDir);
         File[] listOfFiles = folder.listFiles();

         for(int i=0;i<listOfFiles.length;i++){
             if (listOfFiles[i].isDirectory()) {
                 System.out.println("Directory " + listOfFiles[i].getName());
                 directories.add(listOfFiles[i].getName());
             }
         }
         return directories;
    }


    /** getFilesFromRootDir:
     * Gets every single file under this root directory
     *
     * @return
     */
    public ArrayList<String> getFilesFromRootDir(){
        ArrayList<String> array = new ArrayList();
        //Retrieve all files from current folder

        //Loop through list of directories
            //Check if directory has already been
            //Retrieve all files from current directory
        return array;
    }


    /** getFilesFromDir
     * Get all files from given directory
     *
     * @return
     */
    public ArrayList<String> getFilesFromDir(){
        System.out.println("METHOD ACCESSED: SearchEngine.getFilesFromDir()");
        ArrayList<String> files = new ArrayList();
        File folder = new File(this.rootDir);
        File[] listOfFiles = folder.listFiles();

        for(int i=0;i<listOfFiles.length;i++){
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getPath());
                files.add(listOfFiles[i].getPath());

            }
        }
        return files;
    }

    public void addDirectoryList(String dir){
        this.directoryList.add(dir);
    }

    /**
     * Set searchTerm field
     * @param searchTerm String used to search in files
     */
    public void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }

}
