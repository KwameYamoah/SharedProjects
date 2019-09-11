package Program;

import HelperClasses.FileContent;
import HelperClasses.RootDirectory;
import HelperClasses.SearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("START MAIN_");
        // Finds list of files in directory
        String folderName = "Lectures";
        File folder = new File(System.getProperty("user.dir")+"\\"+folderName);
        String dir = System.getProperty("user.dir")+"\\"+folderName;
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> directories = new ArrayList<>();
        directories.add(dir);
        System.out.println(dir);

        //TODO
        //  TO ADD TO UI METHOD WHEN CLICK SEARCH
        //      COPY FROM LINE 28 UNTIL LINE 34
        /**
         * New SearchEngine Object
         */
        String term = "java";   //term they are searching for (currently only works with single words).
        SearchEngine searchEngine = new SearchEngine(term,directories); //directories is an array of all folders
        ArrayList<String> results = new ArrayList<>(searchEngine.search(true));  //true if searching every single sub folder; false if just this folder
        System.out.println(results);    //Results are ordered with highest score first


        System.out.println("END MAIN_");
    }
}
