
package inveredindex;
import java.util.LinkedList;

public class DictEntry {

        int doc_freq = 0; // number of documents that contain the term
        int term_freq = 0; //number of times the term is mentioned in the collection
       LinkedList<Posting> pList = null;

        public DictEntry(int doc_freq,int term_freq) {
                this.doc_freq = doc_freq;
                this.term_freq=term_freq;
        }
}

