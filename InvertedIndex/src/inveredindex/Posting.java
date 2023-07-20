
package inveredindex;

 public class Posting {
        public Posting next = null;
        int docId=0;
        int dtf = 1; // document term frequency

        public Posting(int docId,int dtf) {

            this.docId=docId;
            this.dtf=dtf;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////





