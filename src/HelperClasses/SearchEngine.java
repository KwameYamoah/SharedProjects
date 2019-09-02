package HelperClasses;

import java.util.ArrayList;

/**
 *      SearchEngine class uses a key word to find occurrences in a list of files
 */

public class SearchEngine {

    private String searchTerm;
    private ArrayList directories = new ArrayList();

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
        search();
    }

    /**
     * Sets a new search term before searching files using new search term
     * @param allSubDir True if searching through all sub directories
     */
    public void search(boolean allSubDir){
        search();
    }

    /**
     * Sets a new search term before searching files using new search term
     *  + if searching through every sub directory from root directory
     * @param newSearchTerm Set a new String search term before searching
     * @param allSubDir True if searching through all sub directories
     */
    public void search(String newSearchTerm, boolean allSubDir){
        setSearchTerm(newSearchTerm);
        search();
    }

    /**
     * Searches files using the search term
     */
    public void search(){
        System.out.println("Searching for: "+this.searchTerm);
        //TF-IDF

    }

    /**
     * Method to retrieve all files given the search criteria in parameter
     * @param allSubDir True if searching through all sub directories
     */
    public void getFiles(boolean allSubDir){
        if(allSubDir){
            //get all files from every sub directory starting at root
        }
        else{
            //just get all files in root directory
        }
    }


    /**
     * Set searchTerm field
     * @param searchTerm String used to search in files
     */
    private void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }
}
