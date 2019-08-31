package HelperClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


//Finds all files to be read

/** Finds all files to be searched
 *      Returns the results
 */
public class RootDirectory {
    String name;
    ArrayList<String> files = new ArrayList();           //List of files to be read
    ArrayList<String> children = new ArrayList();      //List of directories accessed

    ArrayList<String> rootDirectories;    //List of all root directories chosen by user
    ArrayList<String> allFiles = new ArrayList();           //List of files to be read
    ArrayList<String> allChildren = new ArrayList();      //List of directories accessed

    boolean singleDirectory;

    HashMap<HashMap<String,String>,Float> results;          //HashMap containing [[File_Name, File_Path],Score]






    //Constructor
    public RootDirectory(String dir){
        this.name = dir;
        this.singleDirectory = true;
    }

    public RootDirectory(ArrayList<String> rootDirectories){
        this.rootDirectories = new ArrayList();
        addToRootDirectories(rootDirectories);
        this.singleDirectory = false;
    }




//    public void doTasks() throws IOException {
//        //For each file, extract content from pdf
//        String content;
//        for (String file: this.fileList) {
//            content = FileAnalyser.extractText(file);
//            dataSetting(content);
//        }
//    }








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
//                 System.out.println("Directory " + listOfFiles[i].getPath());
                 directories.add(listOfFiles[i].getPath());
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
                this.files.add(file);
            }
            return true;
        }
        return false;
    }


    /** addFilesFromRootDir:
     * Adds every single file under this root directory to file
     *
     * @return
     */
    public void addFilesFromRootDir(){
        ArrayList<String> files = new ArrayList();
        //Retrieve all files from current folder
        files = getFilesFromDir(this.name);
        for (String file: files) {
            this.files.add(file);
        }

        //Root directory
        ArrayList<String> toVisit = new ArrayList(); //List of paths to visit

        for (String path: getAllDirectories(this.name)) {
            this.children.add(path);
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
                    this.files.add(file);
                }
            }
            //Check if toVisit directory is empty of sub-directories
            if(containsDirectories(currentDir)){
                for (String path: getAllDirectories(currentDir)){
                    this.children.add(path);
                    toVisit.add(path);
                }
            }
            toVisit.remove(0);      //Remove visited directory
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

    public ArrayList<String> getFiles(){
        return this.files;
    }

    public ArrayList<String> getAllFilesFiles(){ return this.allFiles; }

    public ArrayList<String> getRootDirectories() {return this.rootDirectories; }

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


    private void addToRootDirectories(ArrayList<String> rootDirectories){
        for (String dir: rootDirectories) {
            this.rootDirectories.add(dir);
        }
    }

    private void addToChildren(String dir){
        this.children.add(dir);
    }

    private void addToAllChildren(String dir){
        this.allChildren.add(dir);
    }

    public void resetFiles(){ this.files.clear(); }



}
