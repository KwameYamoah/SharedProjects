package Program;

import HelperClasses.FileContent;
import HelperClasses.RootDirectory;
import HelperClasses.SearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
//TODO
//      Possible implementation idea: Will stop search if found 15 results passed score threshold
//      .
//      More help:  https://codereview.stackexchange.com/questions/28490/searching-word-or-phrase-among-files
//      Library:    Apache Lucene


        System.out.println("START MAIN_");
        // Finds list of files in directory
        String folderName = "Lectures";
        File folder = new File(System.getProperty("user.dir")+"\\"+folderName);
        String dir = System.getProperty("user.dir")+"\\"+folderName;
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> directories = new ArrayList<>();
        directories.add(dir);

//        /**
//         *  New RootDirectory object
//         */
//        RootDirectory directory = new RootDirectory(directories);
////        //Get all files from root directory to sub-levels
////        fileSearcher.getFilesFromRootDir();
//        //Get Files from given String directory path
//        directory.addToFilesFromDirectory(dir);
//
//        //Get test file (first file) from directory
//        String testFile = directory.getFiles().get(0);
//        System.out.println(testFile);
//
//        /**
//         * New FileContent object
//         */
//        FileContent files = new FileContent();
//        //For each file, extract content from pdf
//        for (String filename: directory.getFiles()) {
//            files.addNewFileData(filename);
//        }

        /**
         * New SearchEngine Object
         */
        String term = "information";
        SearchEngine searchEngine = new SearchEngine(term,directories);
        searchEngine.search(false);


        System.out.println("END MAIN_");

    }
}
