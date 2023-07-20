
package inveredindex;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class inverted_list {
    public static ArrayList<String> arr_index = new ArrayList<String>();
    public static void index () throws IOException {

        FileWriter index = new FileWriter("inverted_index.txt");


        for(int j=1;j<10;j++){
        BufferedReader br = new BufferedReader(new FileReader("doc "+j+".txt"));
        String line = "";

        while ((line = br.readLine()) != null) {
            char c='n'; String word="";
       for(int i=0;i<line.length();)   {

            while((c != ' ' )&&(i<line.length())&&(c != '.' )&&(c != ',' )&&(c != ';' )&&(c != '!' )&&(c != '-' )) {

                c = line.charAt(i);
               if ((c != ' ' )&&(c != '.' )&&(c != ',' )&&(c != ';' )&&(c != '!' )&&(c != '-' )){
                word+=c;
               }
                i++;

            }

          arr_index.add(word+' '+j+"\n");
           word="";  c='n';

        }

        }

      }

        Collections.sort(arr_index);
        for (String i : arr_index) {
            index.write(i);
        }
        index.close();
     }
    }
