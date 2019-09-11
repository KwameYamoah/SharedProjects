package HelperClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *      SearchEngine class uses a key word to find occurrences in a list of files
 */

public class SearchEngine {

    private String searchTerm;
    private ArrayList<String> directories;
    private ArrayList<String> files = new ArrayList<>();

    public SearchEngine(String searchTerm, ArrayList<String> directories){
        this.searchTerm = searchTerm;
        this.directories = directories;
    }


    /**
     * Sets a new search term before searching files using new search term
     * @param newSearchTerm Set a new String search term before searching
     */
    public ArrayList<String> search(String newSearchTerm) throws IOException {
        setSearchTerm(newSearchTerm);
        //If no boolean allSubDir given: assume only root directory (false)
        setTypeAllFiles(false);
        ArrayList<String> results = new ArrayList<>(search());
        return results;
    }

    /**
     * Sets a new search term before searching files using new search term
     * @param allSubDir True if searching through all sub directories
     */
    public ArrayList<String> search(boolean allSubDir) throws IOException {
        setTypeAllFiles(allSubDir);
        ArrayList<String> results = new ArrayList<>(search());
        return results;
    }

    /**
     * Sets a new search term before searching files using new search term
     *  + if searching through every sub directory from root directory
     * @param newSearchTerm Set a new String search term before searching
     * @param allSubDir True if searching through all sub directories
     */
    public ArrayList<String> search(String newSearchTerm, boolean allSubDir) throws IOException {
        setTypeAllFiles(allSubDir);
        setSearchTerm(newSearchTerm);
        ArrayList<String> results = new ArrayList<>(search());
        return results;
    }

    /**
     * Searches files using the search term
     */
    private ArrayList<String> search() throws IOException {
        System.out.println("Searching for: "+this.searchTerm);
//        System.out.println(this.directories);
        //TF-IDF

        //Create new fileContents for each document extracted
        FileContent fileContent = new FileContent();
        for (String file: this.files) {
            fileContent.addNewFileData(file);
//            System.out.println(fileContent.getDocsID());
        }

        //Loop through every term and calculate IDF of each term in corpus
        fileContent.calculateAllIDF();

        //Return results which contain
        ArrayList<String> results = new ArrayList<>(fileContent.searchTerm(this.searchTerm));
        return results;
    }

    /**
     * Method to retrieve all files given the search criteria in parameter
     * @param allSubDir True if searching through all sub directories
     */
    private void setTypeAllFiles(boolean allSubDir){
        RootDirectory directory = new RootDirectory(this.directories);

        //If to check every single sub directory
        if(allSubDir){
            //get all files from every sub directory starting at root
            directory.addToFilesFromRootDir();
            //Check if file list if empty
            if(directory.getAllPDFFiles().size()>1){
                System.out.println("More than 1 file in directory");
            }
            else if(directory.getAllPDFFiles().size()==1){
                System.out.println("Only 1 file in directory");
            }
            else if(directory.getAllPDFFiles().isEmpty()){
                System.out.println("No files detected in directory");
            }
            else{
                System.out.println("ELSSEE");
            }
        }
        else{
            //just get all files in root directory
            directory.addToFileFromJustRootDir();
            if(directory.getAllPDFFiles().size()>1){
                System.out.println("More than 1 file in directory");
            }
            else if(directory.getAllPDFFiles().size()==1){
                System.out.println("Only 1 file in directory");
            }
            else if(directory.getAllPDFFiles().isEmpty()){
                System.out.println("No files detected in directory");
            }
            else{
                System.out.println("ELSSEE");
            }
        }
        setFiles(directory.getAllPDFFiles());
    }


    /**
     * Set searchTerm field
     * @param searchTerm String used to search in files
     */
    private void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }

    private void setFiles(ArrayList<String> files) {
        this.files = files;
    }
}
