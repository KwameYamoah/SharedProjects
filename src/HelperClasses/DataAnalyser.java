package HelperClasses;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public static String removeBulletPointFromString(String string) {
        //Create an arrow bullet point string and remove it from content
        char[] chars = {13,10,73};
        String arrowBulletPoint = String.copyValueOf(chars);
        return string.replace(arrowBulletPoint,"");
    }

    public static String extractText(String filename) throws IOException {
        //Loads file
        File file = new File(filename);
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);

        //Removes arrow bullet point as it converts to an I (#73 ascii)
        content = removeBulletPointFromString(content);

        return content;
    }

}
