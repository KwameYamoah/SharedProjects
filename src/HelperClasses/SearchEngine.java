package HelperClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Finds all files to be read

/** Finds all files to be searched
 *      Returns the results
 */
public class SearchEngine {
    ArrayList<String> rootDirectories = new ArrayList();    //List of all root directories chosen by user
    ArrayList<String> fileList = new ArrayList();           //List of files to be read
    ArrayList<String> directoryList = new ArrayList();      //List of directories accessed
    String searchTerm = "";                                 //Search term for search engine
    HashMap<HashMap<String,String>,Float> results;          //HashMap containing [[File_Name, File_Path],Score]

    ArrayList<String> docID = new ArrayList();          //List of all paths
    ArrayList<String> vocab = new ArrayList();          //List of every single vocab from search
    ArrayList<Integer> docLength = new ArrayList();     //Total number of words in document
    HashMap<String,HashMap<String,Integer>> postings;   //HashMap<docID(file path), HashMap<vocab, frequency>>



    //Constructor
    public SearchEngine(ArrayList<String> rootDirectories, String searchTerm){
//        this.rootDirectories = rootDirectories;
        addRootDirectories(rootDirectories);
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


    public void dataSetting(String content){
        //Identify unique vocab
        int docLength=0;
        HashMap<String, Integer> vocabFrequency = new HashMap();

        Matcher m = Pattern.compile("<a.*?/a>|<[^\\>]*>|[\\w'@#]+").matcher(content);
        String token;
        while(m.find()){
//            allMatches.add(m.group().toLowerCase());
            token = m.group();
            if(!this.vocab.contains(token)){
                this.vocab.add(token);
                vocabFrequency.put(token,0);
            }
            else{
                vocabFrequency.put(token,vocabFrequency.get(token)+1);
            }
            docLength++;
        }
        System.out.println(docLength);
        System.out.println(vocab.size());
//        for (String word: vocab) {
//            System.out.println(word);
//        }
//        setPostings();
    }



    public void setPostings(String docID, HashMap<String, Integer> vocabFreq){
        this.postings.put(docID,vocabFreq);
    }


    /** getAllDirectories: Get all directories within current directory (same level)
     *
     * @return
     */
    public ArrayList<String> getAllDirectories(String dir){
        System.out.println("METHOD ACCESSED: SearchEngine.getAllDirectories()");
         ArrayList<String> directories = new ArrayList();
         File folder = new File(dir);
         File[] listOfFiles = folder.listFiles();

         for(int i=0;i<listOfFiles.length;i++){
             if (listOfFiles[i].isDirectory()) {
                 System.out.println("Directory " + listOfFiles[i].getPath());
                 directories.add(listOfFiles[i].getPath());
             }
         }
         return directories;
    }


    /** getFilesFromRootDir:
     * Gets every single file under this root directory
     *
     * @return
     */
    public void getFilesFromRootDir(){
        ArrayList<String> files = new ArrayList();
        //Retrieve all files from current folder
        files = getFilesFromDir(this.rootDirectories.get(0));

        //Root directory
        ArrayList<String> toVisit = new ArrayList(); //List of paths to visit

        for (String path: getAllDirectories(this.rootDirectories.get(0))) {
            this.directoryList.add(path);
            toVisit.add(path);
        }

        String currentDir;
        while(toVisit.size()>0){
            System.out.println(toVisit.size());
            currentDir = toVisit.get(0);
            System.out.println(currentDir);
            //Check if toVisit directory is empty of files
            if(containsFiles(currentDir)){
                for (String file: getFilesFromDir(currentDir)) {
                    this.fileList.add(file);
                }
            }
            //Check if toVisit directory is empty of sub-directories
            if(containsDirectories(currentDir)){
                for (String path: getAllDirectories(currentDir)){
                    this.directoryList.add(path);
                    toVisit.add(path);
                }
            }
            toVisit.remove(0);
        }

    }


    /** getFilesFromDir
     * Get all files from given directory
     *
     * @return
     */
    public ArrayList<String> getFilesFromDir(String dir){
        System.out.println("METHOD ACCESSED: SearchEngine.getFilesFromDir()");
        ArrayList<String> files = new ArrayList();
        File[] listOfFiles = new File(dir).listFiles();

        if(listOfFiles.length > 0){
            for (File path: listOfFiles) {
                if(path.isFile()){
                    System.out.println("File: " + path.getPath());
                    files.add(path.getPath());
                }
            }
        }

        return files;
    }

    private boolean containsItems(File[] files){
        if(files.length==0){
            return false;
        }
        return true;
    }

    private boolean containsFiles(String dir){
        File[] listOfFiles = new File(dir).listFiles();
        if(containsItems(listOfFiles)){
            for (File file: listOfFiles) {
                if(file.isFile()){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsDirectories(String dir){
        File[] listOfFiles = new File(dir).listFiles();
        if(containsItems(listOfFiles)){
            for (File file: listOfFiles) {
                if(file.isDirectory()){
                    return true;
                }
            }
        }
        return false;
    }


    private void addRootDirectories(ArrayList<String> rootDirectories){
        for (String dir: rootDirectories) {
            this.rootDirectories.add(dir);
        }
    }

    private void addDirectoryList(String dir){
        this.directoryList.add(dir);
    }

    /**
     * Set searchTerm field
     * @param searchTerm String used to search in files
     */
    private void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }



}
