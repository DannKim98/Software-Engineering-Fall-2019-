package kz.edu.nu.cs.se;

import java.io.*;
import java.util.ArrayList;


public class MyLanguageParser {
    ArrayList<Object> tokens;
    int index;

    
    public MyLanguageParser(String s){
        StringReader r = new StringReader(s);
        StreamTokenizer st = new StreamTokenizer(r);
        tokens = new ArrayList<>();
        index = 0;

        int curToken = StreamTokenizer.TT_EOF;
        try {
            curToken = st.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(curToken != StreamTokenizer.TT_EOF){
            if(st.ttype==StreamTokenizer.TT_NUMBER){
                tokens.add(st.nval);
            }else if(st.ttype==StreamTokenizer.TT_WORD) {
                tokens.add(st.sval);
            } else {
                tokens.add((char) curToken);
            }

            try {
                curToken = st.nextToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean program(){
        if(isValid()){
            while(isValid()){
            }
            if(index == tokens.size()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean isValidValue() {
        if (index < tokens.size() && tokens.get(index) instanceof Number) {
            index++;
            return true;
        }
        if (index < tokens.size() && tokens.get(index) instanceof String){
            index++;
            return true;
        }
        return false;
    }

    private boolean isValidBrackets(){
        int temp = index;
        if(index < tokens.size() && tokens.get(index).equals('(')){
            index++;
            if(isValidStatement()){
                while(isComma()){
                }
            }
            if(index < tokens.size() && tokens.get(index).equals(')')){
                index++;
                return true;
            }
        }
        index = temp;
        return false;
    }

    private boolean isValidStatement(){
        if(index < tokens.size() && isValidValue()){
            return true;
        }
        if(index < tokens.size() && isValidBrackets()){
            return true;
        }
        return false;
    }

    private boolean isComma() {
        int temp = index;
        if (index < tokens.size() && tokens.get(index).equals(',')){
            index ++;
            if(index < tokens.size() && isValidStatement()){
                return true;
            }
        }index = temp;
        return false;
    }

    private boolean isValid(){
        int temp = index;
        if(isValidStatement()){
            if(index < tokens.size() && tokens.get(index).equals(';')){
                index++;
                return true;
            }
        }
        index = temp;
        return false;
    }

    public void getHTML(){
        if(program() == false){
            return;
        }
        int lvl = -1;
        try {
            PrintWriter w = new PrintWriter("parsedText.html");
            String header = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body><div>";
            w.println(header);

            for (Object token : tokens){
                if (token instanceof String){
                    w.print("<i>"+token+"<i>");
                }
                else if(token.equals('(')){
                    lvl ++;
                    w.print(token+"<span class = 'lv" +lvl+"'>");
                }
                else if(token.equals(')')){
                    lvl --;
                    w.print("</span>"+token);
                }
                else if(token.equals(';')) {
                    w.print(token+"\n<br>");
                }
                else{
                    w.print(token);
                }
            }

            String footer = "<br></div></body></html>";
            w.println(footer);
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
