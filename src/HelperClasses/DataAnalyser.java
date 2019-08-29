package HelperClasses;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


/**
 * Contains classes to:
 *      1. Read files
 *      2. Extract text from file
 *      3. Retrieve term frequency of a file
 *      4. Retrieve score of a file
 *      - Refine search term if contains multiple words
 *      - e.g. searchTerm = "machine learning"
 *      - List of text will conjoin all 'machine learning' as a single word
 *
 *      Can use Apache Lucene
 */
public class DataAnalyser {

    public static void extractText(String filename) throws IOException {
        File file = new File(filename);

        PDDocument doc = PDDocument.load(file);
        String x = new PDFTextStripper().getText(doc);
        System.out.println(x);

        

    }
}
