
package inveredindex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static ArrayList<String> Terms = new ArrayList<String>();
    public static void main(String args[]) throws IOException {
        inverted_list.index();

        PostList.List();
        Scanner myObj = new Scanner(System.in);

        System.out.println("Choose option:");
        System.out.println("get doc of your query press 1");
        System.out.println("get data of one word press 2 ");
        Integer option = myObj.nextInt();
        if(option==1) {
            String term="";
            System.out.println("Enter word needed to search");
          BufferedReader bfn = new BufferedReader(
                    new InputStreamReader(System.in));
            String word = bfn.readLine();

            char c='n';
            for(int i=0;i<word.length();) {
                while ((c != ' ') && (i < word.length()) && (c != '.') && (c != ',') && (c != ';') && (c != '!') && (c != '-')) {

                    c = word.charAt(i);
                    if ((c != ' ') && (c != '.') && (c != ',') && (c != ';') && (c != '!') && (c != '-')) {
                        term += c;
                    }
                    i++;

                }
                Terms.add(term);
                term=""; c='n';

            }


            ArrayList<Integer> list = PostList.Search(Terms);
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }

        }
        else if(option==2){
            System.out.println("Enter word needed to search");
            BufferedReader bfn = new BufferedReader(
                    new InputStreamReader(System.in));
            String Word=bfn.readLine();
            if(PostList.index.get(Word)!=null) {
                System.out.println("Doc frq:" + PostList.index.get(Word).doc_freq + "     Term frq:" + PostList.index.get(Word).term_freq);
                for (int i = 0; i < PostList.index.get(Word).pList.size(); i++) {
                    System.out.println("Doc ID:" + PostList.index.get(Word).pList.get(i).docId);
                }
            }

        }

    }
}

