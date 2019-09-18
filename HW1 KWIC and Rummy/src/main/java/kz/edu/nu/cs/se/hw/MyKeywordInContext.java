package kz.edu.nu.cs.se.hw;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MyKeywordInContext implements KeywordInContext {

    private String name;
	private String pathstring;
	private ArrayList<Indexable> indexed;
	private List<String> toNotConsider;
	
	public MyKeywordInContext(String name, String pathstring) {
        this.name = name;
        this.pathstring = pathstring;
        indexed = new ArrayList<Indexable>();
        toNotConsider = Arrays.asList("A", "AN", "AND", "THE", "IS", "IN", "ON", "AT", "TO", "ABOUT", "AS", "WAS", "WERE", "I", "OF", "MY");
    }

    @Override
    public int find(String word) {
        for (int i = 0; i < indexed.size(); i++) {
        	if (indexed.get(i).getEntry().equals(word.toUpperCase())){
        		return i;
        	}
        }
        return -1;
    }

    @Override
    public Indexable get(int i) {
        return indexed.get(i);
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
    		int index = 0;
    		
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
    	try {
	    	File file = new File(pathstring);
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	    	String txtfiledata = "";
	    	String[] lineByWords;
	    	int index = 0; 
	    			
	    	while ((txtfiledata = br.readLine())!= null) {
	    		lineByWords = txtfiledata.split("[\\s\\p{Punct}]");
	    		index ++;
	    		for (int i = 0; i < lineByWords.length; i++) {
	    			if (lineByWords[i].length() > 0) {
	    				if(!toNotConsider.contains(lineByWords[i].toUpperCase())) {
	    					indexed.add(new MyIndexable(lineByWords[i].toUpperCase(), index));
	    				}	
	    			}	    			
	    		}
	    	}
	    	indexed.sort(new Comparator<Indexable>() {
	            @Override
	            public int compare(Indexable ind1, Indexable ind2) {
	                return ind1.getEntry().compareTo(ind2.getEntry());
	            }
	    	});
	    	br.close();
    	}catch (Exception e) {
    		System.out.println("Something went wrong with files: " + e.toString());
    	}
    }

    @Override
    public void writeIndexToFile() {
    	try {
    		File file = new File(pathstring);
	    	BufferedReader br = new BufferedReader(new FileReader(file));
	    	String txtfiledata = "";
	    	ArrayList<String> lines = new ArrayList<String>();
	    	while ((txtfiledata = br.readLine())!= null) {
	    		lines.add(txtfiledata);
	    	}
	    	
    		BufferedWriter bw = new BufferedWriter(new FileWriter("kwic-" + name + "-Test.html"));
    		String header = "<!DOCTYPE html>\n" + 
    				"<html><head><meta charset=\"UTF-8\"></head><body><div style=\"text-align:center;line-height:1.6\">";
    		String footer = "</div></body></html>";
    		String entry;
    		String line;
    		int lineNumber;
    		bw.write(header);
    		for(Indexable curIndex : indexed) {
    			entry = curIndex.getEntry();
    			lineNumber = curIndex.getLineNumber();
    			line = lines.get(lineNumber-1);
    			for(String cur : line.split("[\\s\\p{Punct}]")){
    				if (cur.length() > 0) {
    					if(cur.toUpperCase().equals(entry)) {
    						bw.write("<a href = \"" + name + "-Test.html#line_" + lineNumber + "\">" + entry + "</a> ");
    					}else {
    						bw.write(cur + " ");
    					}
    				}
    			}bw.write("<br>\n");
    			
    		}
    		bw.write(footer);
    		br.close();
    		bw.close();	
    	}catch (Exception e) {
    		System.out.println("Something went wrong with files: " + e.toString());
    	}
    }
    
}
