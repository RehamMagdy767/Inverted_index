
package inveredindex;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.stream.Collectors;

public class PostList {
    static ArrayList<String> unique_arr_index = (ArrayList<String>)inverted_list.arr_index.clone();
    static ArrayList<Integer> DocsFreq = new ArrayList<Integer>();
    static ArrayList<Integer> TermFreq = new ArrayList<Integer>();
    static ArrayList<ArrayList<Character> > DList = new ArrayList<ArrayList<Character> >();
    static HashMap<String, DictEntry> index= new HashMap<String, DictEntry>();

    public static void List () throws IOException {
        FileWriter list = new FileWriter("Posting_list.txt");

        for (int i = 0; i < unique_arr_index.size(); i++) {
            int docs_freq=1, term_freq=1;;
            ArrayList<Character> temp = new ArrayList<Character>();
            String ele= unique_arr_index.get(i);
            String term=ele.substring(0, ele.indexOf(' '));

            temp.add(ele.charAt(ele.length()-2));

           for (int J = i+1; J < unique_arr_index.size(); J++) {
                String ele2= unique_arr_index.get(J);
                String term2=ele2.substring(0, ele2.indexOf(' '));

                if (term2.equals(term)){
                    unique_arr_index.remove(J);
                    J--;
                    term_freq++;
                    if(ele.charAt(ele.length()-2)!=ele2.charAt(ele2.length()-2)){
                        temp.add(ele2.charAt(ele2.length()-2));
                        docs_freq++;
                    }
                }
            }

            DocsFreq.add(docs_freq);
            TermFreq.add(term_freq);
            DList.add(temp);

        }




        for (int i = 0; i < unique_arr_index.size(); i++) {
            DictEntry entry=new DictEntry(DocsFreq.get(i),TermFreq.get(i));

            entry.pList=new LinkedList<Posting>();

            for (int j = 0; j < DList.get(i).size(); j++) {
                int item=Character.getNumericValue((DList.get(i)).get(j));
               Posting p=new Posting(item,1);

               entry.pList.add(p);


            }
            index.put(unique_arr_index.get(i).substring(0, unique_arr_index.get(i).indexOf(' ')),entry);

        }


        for (int k=0;k<index.size();k++){
            String Key=unique_arr_index.get(k).substring(0, unique_arr_index.get(k).indexOf(' '));
            list.write(Key+" "+index.get(Key).doc_freq);
            for (int g=0;g<index.get(Key).pList.size();g++){
            list.write("     -> "+index.get(Key).pList.get(g).docId+"");
            }
            list.write("\n");
        }

        list.close();
    }

    public static  ArrayList<Integer>  Search (ArrayList<String> Terms) throws IOException {
        ArrayList<Integer> result=new ArrayList<Integer>();
        for (int k=0;k<Terms.size()-1;k++) {

           int p1 =0, p2 = 0;
             while (p1 < index.get(Terms.get(k)).pList.size()  && p2 < index.get(Terms.get(k+1)).pList.size()) {
                if ((index.get(Terms.get(k)).pList.get(p1).docId == index.get(Terms.get(k+1)).pList.get(p2).docId)&&(result.contains(index.get(Terms.get(k)).pList.get(p1).docId)|| (result.isEmpty())))
                { result.add(index.get(Terms.get(k)).pList.get(p1).docId);
                p1 =p1 + 1;
                p2 =p2 + 1;
                }
               else if (index.get(Terms.get(k)).pList.get(p1).docId > index.get(Terms.get(k)).pList.get(p1).docId)
                {
                    p2 =p2 + 1;
                }

               else{
                p1 =p1 + 1;
               }

            }



        }
       return result;
    }


}
