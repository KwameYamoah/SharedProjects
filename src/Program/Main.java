package Program;

import HelperClasses.DataAnalyser;
import HelperClasses.SearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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


        System.out.println("START MAIN_");
        // Finds list of files in directory
        String folderName = "Lectures";
        File folder = new File(System.getProperty("user.dir")+"\\"+folderName);
        String dir = System.getProperty("user.dir")+"\\"+folderName;
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> directories = new ArrayList();
        directories.add(dir);

        SearchEngine fileSearcher = new SearchEngine(directories,"java");
        fileSearcher.getFilesFromRootDir();


        /////TEST//////
        try {
            DataAnalyser.extractText(dir+"\\ML Lecture 1 Introduction.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /////////////



        System.out.println("END MAIN_");

    }
}
