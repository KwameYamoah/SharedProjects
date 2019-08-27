package Program;

import HelperClasses.SearchEngine;
import User_Interface.UI;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
//TODO   1. Read pdf files
//       2. Convert pdf to text
//       3. Split text content into individual words
//       4. Filter out ascii symbols or punctuation
//         4.a. Memory-management memory management (Punctuation management)
//       5. Get word term frequency
//       6. Get TF IDF (formula)
//       7. UI to apply search and print out results
//          7. Table of results: Contains link - When clicked, opens directory of where file is at.



        // Finds list of files in directory
        String folderName = "Lectures";
        File folder = new File(System.getProperty("user.dir")+"\\"+folderName);
        String dir = System.getProperty("user.dir")+"\\"+folderName;
        File[] listOfFiles = folder.listFiles();


        SearchEngine fileSearcher = new SearchEngine(dir,"java");





        //Need to use a DFS search to traverse directories and retrieve list of all relevant files to be read





    }
}
