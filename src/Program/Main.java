package Program;

import HelperClasses.SearchEngine;
import User_Interface.UI;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
//TODO   1. Read pdf files
//       2. Convert pdf to text
//       3. Tokenise text content into individual words
//       4. Get required data from file
//       5. Output data to txt file as json format
//              Individual output: [DocumentID, DocumentLength, Postings(frequency), Vocabulary]
//       6. Open each document and apply search_term
//       7. UI to print out results
//          7. Table of results: Contains link - When clicked, opens directory of where file is at.
//        .
//        .
//      ### Need to discuss whether should search file immediately or save postings to file and then read postings. ###
//        .
//        .
//      Possible implementation idea: Will stop search if found 15 results passed score threshold
//      .
//      More help:  https://codereview.stackexchange.com/questions/28490/searching-word-or-phrase-among-files
//      Library:    Apache Lucene


        // Finds list of files in directory
        String folderName = "Lectures";
        File folder = new File(System.getProperty("user.dir")+"\\"+folderName);
        String dir = System.getProperty("user.dir")+"\\"+folderName;
        File[] listOfFiles = folder.listFiles();


        SearchEngine fileSearcher = new SearchEngine(dir,"java");
//        fileSearcher.getAllDirectories();
        fileSearcher.getFilesFromDir();





        //Need to use a DFS search to traverse directories and retrieve list of all relevant files to be read





    }
}
