package HelperClasses;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *      FileAnalyser class extracts content from a file
 *      Stores details about file
 */

public class FileContent {

    private String filename;    //ID of current document read filepath

    private HashMap<String, Integer> docsID = new HashMap<>();          //Find docID with filename(path)
    private HashMap<String, Double> vocab_idf = new HashMap<>();        //List of all unique tokens with IDF score
    private HashMap<Integer,Integer> docLength = new HashMap<>();       //Total number of words per document
    private HashMap<String, HashMap<Integer,Integer>> results_freq = new HashMap<>();   //Dictionary of words: List of terms per (docID:freq)


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

        //Close file
        doc.close();

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

        //CALCULATE TF then add to results

        //Create a document ID using filename(path)
        addToDocsID(this.filename);
        //Add length of document
        addToDocLength(docLength);
        //Add results
        addToResults(term_freq);

        //Save results to file
//        outputFileResults();
    }

    /**
     * Add to this.results
     * @param term_freq HashMap of Term and Frequency of occurrence
     */
    private void addToResults(HashMap<String, Integer> term_freq){
        //For each term
        for (String term: term_freq.keySet()) {
            HashMap<Integer,Integer> docID_tf = new HashMap<>();
            Integer freq = term_freq.get(term);

            //If doc HashMap has not been appended yet
            if(!this.results_freq.containsKey(term)){
                //Term to be created
                docID_tf.put(getDocID(this.filename),freq);
                addToVocab_idf(term);
            } //Else append document tf to term HashMap
            else{
                //Get the existing hashmap and add
                docID_tf = this.results_freq.get(term);
                docID_tf.put(getDocID(this.filename),freq);
            }
            this.results_freq.put(term,docID_tf);

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

    ArrayList searchTerm(String term){
        HashMap<Integer, Double> tf_idf_docs = new HashMap<>();

        //For each docID that contains the term/
        for (Map.Entry<Integer, Integer> map: this.results_freq.get(term).entrySet()) {
            Integer docID = map.getKey();
            double freq = map.getValue();
            double docLength = getDocLength(docID);

            double tf = freq/docLength;
            double tf_idf = tf/this.vocab_idf.get(term);

            tf_idf_docs.put(docID,tf_idf);
        }
        //Sort HashMap by value (Linked HashMap)
        LinkedHashMap ordered_tf_idf = new LinkedHashMap(sortHashMapByValues(tf_idf_docs));
//        System.out.println(ordered_tf_idf);

        //Get filename from order append ordered list to ArrayList<String>
        return new ArrayList<>(getFilesFromHashMap(ordered_tf_idf));
    }

    private void addToVocab_idf(String vocab){
        //If not in vocab_idf then add
        if(!this.vocab_idf.containsKey(vocab)){
            this.vocab_idf.put(vocab,0.0);
        }
    }

    void calculateAllIDF(){
        //Calculate IDF of every single term in vocab_idf
        for (String term: this.vocab_idf.keySet()) {
            double numDocs = getDocsID().size();
            double numDocsWithTerm = getResults().get(term).size();
            double idf = Math.log10(numDocs/numDocsWithTerm);
//            System.out.println(term+": "+numDocs+" / "+numDocsWithTerm + " IDF: " + idf);
            this.vocab_idf.put(term,idf);
        }
    }

    private LinkedHashMap<Integer,Double> sortHashMapByValues(HashMap<Integer, Double> map){
        List<Integer> docID = new ArrayList<>(map.keySet());
        List<Double> score = new ArrayList<>(map.values());
        score.sort(Collections.reverseOrder());

        LinkedHashMap<Integer, Double> sortedMap = new LinkedHashMap<>();

        for (Double value : score) {
            Iterator<Integer> keyIt = docID.iterator();
            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                Double comp1 = map.get(key);
                Double comp2 = value;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, value);
                    break;
                }
            }
        }
        return sortedMap;
    }

    private ArrayList<String> getFilesFromHashMap(LinkedHashMap<Integer, Double> map){
        ArrayList<String> orderedFiles = new ArrayList<>();
        for (Integer docID: map.keySet()) {
            String filename = getFilenameFromDocID(docID);
            orderedFiles.add(filename);
        }
        return orderedFiles;
    }

    /**
     *
     * @return this.fileName String
     */
    public String getFilename() {
        return this.filename;
    }

    private String getFilenameFromDocID(Integer docid){
        for (String filename: this.docsID.keySet()) {
            if(docid == this.docsID.get(filename)){
                return filename;
            }
        }
        return "";
    }

    /**
     *
     * @param filepath Filepath of document
     * @return Integer value of document ID
     */
    private Integer getDocID(String filepath){
        return this.docsID.get(filepath);
    }

    /**
     *
     * @return this.vocab
     */
    private HashMap<String,Double> getVocab() {
        return this.vocab_idf;
    }

    /**
     *
     * @return this.docsID
     */
    private HashMap<String, Integer> getDocsID() {
        return this.docsID;
    }

    /**
     *
     * @return this.results
     */
    private HashMap<String, HashMap<Integer, Integer>> getResults() {
        return this.results_freq;
    }

    /**
     *
     * @return this.docLength
     */
    private HashMap<Integer, Integer> getDocLength() {
        return this.docLength;
    }

    /**
     *
     * @return this.docLength
     */
    private Integer getDocLength(Integer docID) {
        return this.docLength.get(docID);
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
        this.docLength.put(getDocID(this.filename),length);
    }

    /**
     *
     * @param path String file path
     */
    private void setFilename(String path){
        this.filename = path;
    }

}
