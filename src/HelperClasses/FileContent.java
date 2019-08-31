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
 *      FileAnalyser class extracts content from a file
 *      Stores details about file
 */

public class FileContent {

    ArrayList<String> docID = new ArrayList();          //List of all paths
    ArrayList<String> vocab = new ArrayList();          //List of every single vocab from search
    ArrayList<Integer> docLength = new ArrayList();     //Total number of words in document
    HashMap<String,HashMap<String,Integer>> postings;   //HashMap<docID(file path), HashMap<vocab, frequency>>

    public FileContent(){

    }

    public static String removeBulletPointFromString(String string) {
        //Create an arrow bullet point string and remove it from content
        char[] chars = {13,10,73};
        String arrowBulletPoint = String.copyValueOf(chars);
        return string.replace(arrowBulletPoint,"");
    }

    public static String extractText(String filename) throws IOException {
        //Loads file
        java.io.File file = new java.io.File(filename);
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);

        //Removes arrow bullet point as it converts to an I (#73 ascii)
        content = removeBulletPointFromString(content);

        return content;
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

}
