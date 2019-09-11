package HelperClasses;

import java.io.File;
import java.util.ArrayList;


//Finds all files to be read

/** Finds all files to be searched
 *      Returns the results
 */
public class RootDirectory {
    private String name;
    private ArrayList<String> files = new ArrayList<>();          //List of files to be read
    private ArrayList<String> children = new ArrayList<>();       //List of directories accessed

    private ArrayList<String> rootDirectories;                  //List of all root directories chosen by user
    private ArrayList<String> allFiles = new ArrayList<>();       //List of files to be read
    private ArrayList<String> allChildren = new ArrayList<>();    //List of directories accessed

    private boolean singleDirectory;


    /** CONSTRUCTOR
     *
     * @param dir String of directory filepath
     */
    public RootDirectory(String dir){
        this.name = dir;
        this.singleDirectory = true;
    }

    public RootDirectory(ArrayList<String> rootDirectories){
        this.name = rootDirectories.get(0);
        this.rootDirectories = new ArrayList<>();
        addToRootDirectories(rootDirectories);
        this.singleDirectory = false;
    }


    /** getAllDirectories: Get all directories within current directory (same level)
     *
     * @return ArrayList of String containing file paths of directories
     */
    private ArrayList<String> getAllDirectories(String dir){
         ArrayList<String> directories = new ArrayList<>();
         File folder = new File(dir);
         File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isDirectory()) {
//                 System.out.println("Directory " + listOfFiles[i].getPath());
                directories.add(file.getPath());
            }
        }
         return directories;
    }

    /** Adds all files from a given directory to fileList
     *
     * @param dir String of directory path
     * @return Boolean true if files were added
     */
    public boolean addFilesFromDir(String dir){
        if(containsFiles(dir)){
            for (String file: getFilesFromDir(dir)) {
                addToFiles(file);
            }
            return true;
        }
        return false;
    }

    /**
     *  Adds every single file from just this root directory
     */
    public void addToFileFromJustRootDir(){
        File[] listOfFiles = new File(this.name).listFiles();

        if(listOfFiles.length > 0){
            for (File file: listOfFiles) {
                if(file.isFile()){
                    addToFiles(file.getPath());
                    addToAllFiles(file.getPath());
                }
            }
        }
    }


    /** addFilesFromRootDir:
     * Adds every single file under this root directory to file
     *
     */
    public void addToFilesFromRootDir(){
        //Retrieve all files from current folder
        ArrayList<String> files = getFilesFromDir(this.name);
        for (String file: files) {
            addToFiles(file);
            addToAllFiles(file);
        }

        //Root directory
        ArrayList<String> toVisit = new ArrayList<>(); //List of paths to visit

        for (String path: getAllDirectories(this.name)) {
            addToChildren(path);
            toVisit.add(path);
        }

        String currentDir;
        while(toVisit.size()>0){
            currentDir = toVisit.get(0);
            //Check if toVisit directory is empty of files
            if(containsFiles(currentDir)){
                for (String file: getFilesFromDir(currentDir)) {
                    //If not in this.allFiles: then add
                    if(!isInAllFiles(file)){
                        addToAllFiles(file);
                    }

                }
            }
            //Check if toVisit directory is empty of sub-directories
            if(containsDirectories(currentDir)){
                for (String path: getAllDirectories(currentDir)){
                    //If not in this.allChildren: then add
                    if(!isInAllChildren(path)){
                        addToAllChildren(path);
                        toVisit.add(path);
                    }
                }
            }
            toVisit.remove(0);      //Remove visited directory
        }

    }


    /** getFilesFromDir
     * Get all files from given single String directory
     *
     * @return ArrayList of files
     */
    private ArrayList<String> getFilesFromDir(String dir){
        ArrayList<String> files = new ArrayList<>();
        File[] listOfFiles = new File(dir).listFiles();

        if(listOfFiles.length > 0){
            for (File path: listOfFiles) {
                if(path.isFile()){
                    files.add(path.getPath());
                }
            }
        }
        return files;
    }

    /**
     *  Add all files from given directory to this.files
     * @param dir String
     */
    public void addToFilesFromDirectory(String dir){
        File[] listOfFiles = new File(dir).listFiles();

        if(listOfFiles.length > 0){
            for (File path: listOfFiles) {
                if(path.isFile()){
                    this.files.add(path.getPath());
                }
            }
        }
    }

    private String getFileType(String filename){
        int lastIndex = filename.lastIndexOf(".");
        if(lastIndex == -1){
            return "";
        }
        return filename.substring(lastIndex);
    }

    /**
     * Returns True if directory path already in this.allChildren
     * @return Boolean
     */
    private boolean isInAllChildren(String dir){
        return this.allChildren.contains(dir);
    }

    /**
     * Returns True if file path already in this.allFiles
     * @return Boolean
     */
    private boolean isInAllFiles(String dir){
        return this.allFiles.contains(dir);
    }



    /**
     * Returns all file paths saved in directory
     * @return this.files
     */
    public ArrayList<String> getFiles(){ return this.files;}

    /**
     * Returns all file paths saved in directory and sub directories
     * @return this.allFiles
     */
    public ArrayList<String> getAllFiles(){ return this.allFiles; }

    public ArrayList<String> getAllPDFFiles(){
        ArrayList<String> arr = new ArrayList<>();
        for (String file: this.allFiles) {
            if(getFileType(file).equals(".pdf")){
                arr.add(file);
            }
        }
        return arr;
    }

    /**
     * Returns list of saved directories passed in to search in
     * @return this.rootDirectories
     */
    public ArrayList<String> getRootDirectories() { return this.rootDirectories; }

    /**
     * Returns True if contains item; False if empty
     * @param files List of files
     * @return boolean
     */
    private boolean containsItems(File[] files){ return files.length != 0; }

    /**
     * Return True if directory contains a file-type
     * @param dir String directory filepath
     * @return boolean
     */
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

    /**
     * Returns True if directory contains a Directory-type
     * @param dir String directory filepath
     * @return boolean
     */
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

    /**
     * Adds all objects from given ArrayList to rootDirectory
     * @param rootDirectories ArrayList
     */
    private void addToRootDirectories(ArrayList<String> rootDirectories){
        this.rootDirectories.addAll(rootDirectories);
    }

    /**
     * Adds a filepath to this.files
     * @param dir String directory filepath
     */
    private void addToFiles(String dir) {this.files.add(dir);}

    /**
     * Adds a filepath to this.allFiles
     * @param dir String directory filepath
     */
    private void addToAllFiles(String dir) {this.allFiles.add(dir);}

    /**
     * Adds a filepath to this.children
     * @param dir String directory filepath
     */
    private void addToChildren(String dir){this.children.add(dir);}

    /**
     * Adds a filepath to this.allChildren
     * @param dir String directory filepath
     */
    private void addToAllChildren(String dir){this.allChildren.add(dir);}

    /**
     * Clears this.files of any items
     */
    public void resetFiles(){ this.files.clear(); }


    public void setName(String name) {
        this.name = name;
    }
}
