package kz.edu.nu.cs.se.hw;

public class MyIndexable implements Indexable {
	private String entry;
	private int lineNumber;
	
	public MyIndexable(String entry, int lineNumber) {
		this.entry = entry;
		this.lineNumber = lineNumber;
	}
	
	@Override
	public String getEntry() {
		return entry;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}



}