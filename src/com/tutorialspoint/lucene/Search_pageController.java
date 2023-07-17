
package com.tutorialspoint.lucene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import javax.swing.*;

public class Search_pageController   {
    String indexDir = "E:\\lucene\\Index";
    String dataDir = "E:\\lucene\\novels";
    Indexer indexer;
    Searcher searcher;

       @FXML
    public TextField txt_search;
    @FXML
    private TextArea txt_Result_found;
    @FXML
    private TextField txt_found;

    @FXML
    private TextField txt_index;

    @FXML
    void search_key(ActionEvent event) {

        LuceneTester tester;
        try {
            tester = new LuceneTester();
            createIndex();
            if(txt_search.getText()!=""){
                search(txt_search.getText());
            }else{
                JOptionPane.showMessageDialog(null," please enter word to search ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        if(txt_search.getText()!="") {
            txt_index.setText(numIndexed + " File indexed"+ "\n");
        }
    }


    public void search(String searchQuery) throws IOException, ParseException {
        txt_Result_found.setText("");
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

        txt_found.setText(hits.totalHits +
                " documents found");
        if(hits.totalHits==0){
            JOptionPane.showMessageDialog(null,"Your search did not return any results  "+"\n"+"Here are some suggestions for more meaningful research : "+"\n"+"---> Make sure you write the words correctly ."+"\n"+"---> Try Different words .");
        }

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            txt_Result_found.appendText("Name: "
                    + doc.get(LuceneConstants.FILE_NAME));
            txt_Result_found.appendText("\n");
            txt_Result_found.appendText("File: "
                    + doc.get(LuceneConstants.FILE_PATH));
            txt_Result_found.appendText("\n"+"\n");

        }
        searcher.close();
    }
    @FXML
    void exit(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Thanks for using our engine ");
        System.exit(0);

    }
    public void entered(Event e){
        ((Button)e.getSource()).setScaleX(1.1);
        ((Button)e.getSource()).setScaleY(1.1);
    }
    public void exitt(Event e){
        ((Button)e.getSource()).setScaleX(1);
        ((Button)e.getSource()).setScaleY(1);
    }

    @FXML
    void clear(ActionEvent event) {

        txt_search.setText("");
        txt_Result_found.setText("");
        txt_found.setText("");
        txt_index.setText("");
    }

}
