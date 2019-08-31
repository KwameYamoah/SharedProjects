package HelperClasses;

/**
 *      SearchEngine class uses a key word to find occurrences in a list of files
 */

public class SearchEngine {

    String searchTerm;

    public SearchEngine(String searchTerm){
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
        //TF-IDF
    }


    /**
     * Set searchTerm field
     * @param searchTerm String used to search in files
     */
    private void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }
}
