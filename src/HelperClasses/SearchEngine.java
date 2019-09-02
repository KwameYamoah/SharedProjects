package HelperClasses;

import java.io.File;
import java.util.ArrayList;

/**
 *      SearchEngine class uses a key word to find occurrences in a list of files
 */

public class SearchEngine {

    private String searchTerm;
    private ArrayList<String> directories;
    private ArrayList<String> files = new ArrayList<>();
    private FileContent fileContent = new FileContent();

    public SearchEngine(String searchTerm, ArrayList<String> directories){
        this.searchTerm = searchTerm;
        this.directories = directories;
    }

    /**
     * SearchEngine to create a RootDirectory class with 'directories'
     * SearchEngine to create a FileContent class to access content of files
     *
     * Apply search to fields of FileContent object.
     */

    //different: {0=1}, {1=3}
    //different: 0=1, 1=3
    //different:   0, 1 ,1,3
    //different, 0,1,1,3,
    //[different, 0,1,1,3]
    //Arraylist<Integer,Integer> test = new Arraylist<>();
    //loop start 1 till end
    //0 1
    //1 3
    //test.add(0,1);
    //test.add(1,3)
    //results.put("different", test);



    /**
     * Sets a new search term before searching files using new search term
     * @param newSearchTerm Set a new String search term before searching
     */
    public void search(String newSearchTerm){
        setSearchTerm(newSearchTerm);
        //If no boolean allSubDir given: assume only root directory (false)
        setTypeAllFiles(false);
        search();
    }

    /**
     * Sets a new search term before searching files using new search term
     * @param allSubDir True if searching through all sub directories
     */
    public void search(boolean allSubDir){
        setTypeAllFiles(allSubDir);
        search();
    }

    /**
     * Sets a new search term before searching files using new search term
     *  + if searching through every sub directory from root directory
     * @param newSearchTerm Set a new String search term before searching
     * @param allSubDir True if searching through all sub directories
     */
    public void search(String newSearchTerm, boolean allSubDir){
        setTypeAllFiles(allSubDir);
        setSearchTerm(newSearchTerm);
        search();
    }

    /**
     * Searches files using the search term
     */
    private void search(){
        System.out.println("Searching for: "+this.searchTerm);
        System.out.println(this.directories);
        //TF-IDF

    }

    /**
     * Method to retrieve all files given the search criteria in parameter
     * @param allSubDir True if searching through all sub directories
     */
    public void setTypeAllFiles(boolean allSubDir){
        RootDirectory directory = new RootDirectory(this.directories);
        //If more than 1 root directory
        //Else if only 1 root directory
        //Else if empty ArrayList

        //TODO
        //  Get filepaths/ single filepath
        //  Check if files exist

        if(allSubDir){
            //get all files from every sub directory starting at root
            directory.addToFilesFromRootDir();
            if(directory.getAllFiles().size()>1){
                System.out.println("More than 1 file in directory");
                setFiles(directory.getAllFiles());
            }
            else if(directory.getAllFiles().size()==1){
                System.out.println("Only 1 file in directory");
            }
            else if(directory.getAllFiles().isEmpty()){
                System.out.println("No files detected in directory");
            }
            else{
                System.out.println("ELSSEE");
            }
        }
        else{
            //just get all files in root directory
            directory.addToFileFromJustRootDir();
            if(directory.getAllFiles().size()>1){
                System.out.println("More than 1 file in directory");
            }
            else if(directory.getAllFiles().size()==1){
                System.out.println("Only 1 file in directory");
            }
            else if(directory.getAllFiles().isEmpty()){
                System.out.println("No files detected in directory");
            }
            else{
                System.out.println("ELSSEE");
            }
        }
        setFiles(directory.getAllFiles());
    }


    /**
     * Set searchTerm field
     * @param searchTerm String used to search in files
     */
    private void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }
}
