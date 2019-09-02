package HelperClasses;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *      FileAnalyser class extracts content from a file
 *      Stores details about file
 */

public class FileContent {

    private String filename;    //ID of current document read filepath

    private HashMap<String, Integer> docsID = new HashMap<>();    //Find docID with filename(path)
    private ArrayList<String> vocab = new ArrayList<>();          //List of all unique tokens
    private ArrayList<Integer> docLength = new ArrayList<>();     //Total number of words in document
    private HashMap<String, ArrayList<HashMap<Integer,Integer>>> results = new HashMap<>();   //Dictionary of words: List of occurrences per (docID:freq)

    //Levels:
    //  3 unique variables: Integer DocID, Integer term_frequency, String Term;
    //  1. a = HashMap<Integer,Integer>          :   Integer DocID, Integer term_frequency
    //  2. b = (String, ArrayList<a>)           :   String Term, ArrayList(a)
    //  3. c = HashMap<String, ArrayList(b)>    :   HashMap<Term, ArrayList(a)

    public FileContent(){

    }

    /**
     * Removes arrow bullet point from string content.
     *  When extracted using PDFbox, appears as an 'I'
     * @param string String content of extracted document
     * @return String without arrow bullet point
     */
    private static String removeBulletPointFromString(String string) {
        //Create an arrow bullet point string and remove it from content
        char[] chars = {13,10,73};
        String arrowBulletPoint = String.copyValueOf(chars);
        return string.replace(arrowBulletPoint,"");
    }

    /**
     * Join words together by removing certain punctuation
     * @param content String content of extracted document
     * @return String content
     */
    private String joinWordsInContent(String content){
        // Join words with a dash in them
        // Join words with underscore in them
        content = content.replace("-","").replace("_","");
        return content;
    }

    /**
     * Extract text from a give pdf file document using PDFbox library
     * @param filename String filename of document to be read
     * @return extracted String content that is filtered
     * @throws IOException if file cannot be read
     */
    private String extractText(String filename) throws IOException {
        //Loads file
        java.io.File file = new java.io.File(filename);
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);

        //Removes arrow bullet point as it converts to an I (#73 ascii)
        return removeBulletPointFromString(content);
    }

    /**
     * Add new File from given filename
     * @param filename String name of document file to be read
     * @throws IOException if file given cannot be read
     */
    public void addNewFileData(String filename) throws IOException {
        setFilename(filename);
        String content = extractText(filename);
        content = joinWordsInContent(content);

        //Identify unique vocab
        int docLength=0;
        //Dictionary storing term:total_occurrence per run
        HashMap<String, Integer> term_freq = new HashMap<>();

        Matcher m = Pattern.compile("<a.*?/a>|<[^\\>]*>|[\\w'@#]+").matcher(content);
        String token;
        while(m.find()){
            token = m.group().toLowerCase();
            //Add unique token to vocab
            if(!term_freq.containsKey(token)){
                term_freq.put(token,1);
            }
            else{
                term_freq.put(token,term_freq.get(token)+1);
            }
            docLength++;
        }

        //Create a document ID using filename(path)
        addToDocsID(this.filename);
        //Add length of document
        addToDocLength(docLength);
        //Add results
        addToResults(term_freq);

        //Save results to file
        outputFileResults();
    }

    /**
     * Add to this.results
     * @param term_freq HashMap of Term and Frequency of occurrence
     */
    private void addToResults(HashMap<String, Integer> term_freq){
        for (String term: term_freq.keySet()) {
            HashMap<Integer,Integer> docID_freq = new HashMap<>();
            Integer freq = term_freq.get(term);
            docID_freq.put(getDocID(this.filename),freq);
            //If has not been appended
            if(!this.results.containsKey(term)){
                //New ArrayList to be added to HashMap
                ArrayList<HashMap<Integer,Integer>> newArr = new ArrayList<>();
                newArr.add(docID_freq);
                this.results.put(term,newArr);
            }
            else{ //Add HashMap to existing ArrayList
                this.results.get(term).add(docID_freq);
                this.vocab.add(term);
            }

        }
    }

    /**
     * Method to output fields to .txt files
     * @throws FileNotFoundException If file not found
     * @throws UnsupportedEncodingException If file cannot be read
     */
    private void outputFileResults() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("docids.txt", "UTF-8");
        writer.print(getDocsID());
        writer.close();
        writer = new PrintWriter("vocab.txt", "UTF-8");
        writer.print(getVocab());
        writer.close();
        writer = new PrintWriter("docLength.txt", "UTF-8");
        writer.print(getDocLength());
        writer.close();
        writer = new PrintWriter("results.txt", "UTF-8");
        writer.print(getResults());
        writer.close();
    }

    /**
     *
     * @return this.fileName String
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     *
     * @param filepath Filepath of document
     * @return Integer value of document ID
     */
    public Integer getDocID(String filepath){
        return this.docsID.get(filepath);
    }

    /**
     *
     * @return this.vocab
     */
    public ArrayList<String> getVocab() {
        return this.vocab;
    }

    /**
     *
     * @return this.docsID
     */
    public HashMap<String, Integer> getDocsID() {
        return this.docsID;
    }

    /**
     *
     * @return this.results
     */
    public HashMap<String, ArrayList<HashMap<Integer, Integer>>> getResults() {
        return this.results;
    }

    /**
     *
     * @return this.docLength
     */
    public ArrayList<Integer> getDocLength() {
        return this.docLength;
    }

    /**
     *
     * @param filename file path
     */
    private void addToDocsID(String filename){
        this.docsID.put(filename, this.docsID.size());
    }

    /**
     *
     * @param length Integer length of document
     */
    private void addToDocLength(Integer length){
        this.docLength.add(length);
    }

    /**
     *
     * @param path String file path
     */
    private void setFilename(String path){
        this.filename = path;
    }

}
