// https://www.codota.com/web/assistant/code/rs/5c659a4a1095a5000165cadc#L53

package org.apache.lucene.demo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
 
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.SuppressForbidden;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;


public final class SimpleMetrics {
    public static void main(String[] args) {
        String indexPath = "index";
        System.out.println("Enter a term: ");
        Scanner input = new Scanner(System.in);
        String term = input.nextLine();
        String field = "contents";
        
        try {
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Term newTerm = new Term(field, term);

            IndexReader reader = DirectoryReader.open(dir);
            System.out.printf(Locale.ROOT, "%s:%s \t totalTF = %,d \t doc freq = %,d \n", newTerm.field(), newTerm.text(), reader.totalTermFreq(newTerm), reader.docFreq(newTerm)); 
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}