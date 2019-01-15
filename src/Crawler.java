import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Crawler {
	private ArrayList<String> pagesToVisit, pagesVisited;
	 static ArrayList<Integer> pathLengths = new ArrayList<Integer>();
	 static ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
	private int count;
	private String string1, string2;
	public Crawler(String s1, String s2, int count, ArrayList<String> a) {
		string1 = s1; string2 = s2;
		this.count = count + 1;
		pagesVisited = new ArrayList<String>();
		for (String s : a)
			pagesVisited.add(s);
		pagesVisited.add(s1);
		pagesToVisit = new ArrayList<String>();
	}
	public Crawler(String s1, String s2) {
		string1 = s1; string2 = s2;
		this.count = 0;
		pagesVisited = new ArrayList<String>();
		pagesVisited.add(s1);
		pagesToVisit = new ArrayList<String>();
	}
	public void getLinks() {
		Page page = new Page(string1);
		page.readPage();
		FileReader filereader = null;
		try {
			filereader = new FileReader("Output.txt");
		} catch (FileNotFoundException e) {
			System.out.println("c couldn't open output");
		}
		BufferedReader reader = new BufferedReader(filereader);
		String line, temp;
		int index = 0;
		try {
			while((line = reader.readLine()) != null) {
				index = 0;
				while ((index = line.indexOf("/wiki/", index)) > -1) {
					if (line.indexOf("\"",index) > -1) {
						temp = line.substring(index+6, line.indexOf("\"",index));
						if(isAlpha(temp) && !pagesVisited.contains(temp) && !pagesToVisit.contains(temp)) {
							pagesToVisit.add(temp);
						}
					}
					index++;
				}
			}
		} catch (IOException e) {
			System.out.println("c is reading line");
		}
		System.out.println("jump: " + count + " has visited pages: " + pagesVisited + " and will visit: " + pagesToVisit);
		try {
			reader.close();
		} catch (IOException e) {
			System.out.print("c close 1");}
		try {
			filereader.close();
		} catch (IOException e) {
			System.out.print("c close 2");}
		if (pagesToVisit.contains(string2)) {
			System.out.println("path was found in " + (count + 1) + " jumps. ");
			pathLengths.add(count+1);
			pagesVisited.add(string2);
			paths.add(pagesVisited);
		}
		else if (count > Window.maxCount)
			System.out.println("This path did not reach the destination.");
		else {
			for (String s : pagesToVisit) {
			Crawler next = new Crawler(s, string2, count, pagesVisited);
			next.getLinks();
			}
		}
	}
	public boolean isAlpha(String s) {
		for (int i = 0; i < s.length(); i++)
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (s.charAt(i) == '_' || s.charAt(i) == ',');
				else
				return false;}
		return true;
	}
}