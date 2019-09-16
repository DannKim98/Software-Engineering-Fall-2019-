package kz.edu.nu.cs.se.hw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MyKeywordInContext implements KeywordInContext {

    private String name;
	private String pathstring;

	public MyKeywordInContext(String name, String pathstring) {
        this.name = name;
        this.pathstring = pathstring;
    }

    @Override
    public int find(String word) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Indexable get(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void txt2html() {
    	try {
    		File file = new File(pathstring);
    		BufferedWriter bw = new BufferedWriter(new FileWriter(name + "-Test.html"));
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		String header = "<!DOCTYPE html>\n" + 
    				"<html><head><meta charset=\"UTF-8\"></head><body>\n" +
    				"<div>\n";
    		String footer = "</div></body></html>";
    		ArrayList<String> txtByLine = new ArrayList<String>();
    		String txtfiledata = "";
    		int index = 0 ;
    		
    		bw.write(header);
    		while ((txtfiledata = br.readLine())!= null) {
    			txtByLine.add(txtByLine.size(),txtfiledata);
                index++;
    		}
    		for (int i = 0; i < index; i++) {
    			int line = i+1;
    			bw.write(txtByLine.get(i) + "<span id=\"line_" + line + "\">&nbsp&nbsp["+ line +"]</span><br>\n");
    		}
    		
    		bw.write(footer);
    		
    		bw.close();
    		br.close();
    	}
    	catch (Exception e) {
    		System.out.println("Something went wrong with files: " + e.toString());
    	}

    }

    @Override
    public void indexLines() {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeIndexToFile() {
        // TODO Auto-generated method stub

    }
    
}
