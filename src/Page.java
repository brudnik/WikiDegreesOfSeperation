import java.net.*;
import java.io.*;
public class Page {
	private final String path = "https://en.wikipedia.org/wiki/";
	private String pageName;
	public Page(String s) {
		pageName = s;
	}
    public void readPage() {
    	PrintWriter writer = null;
        URL url = null;
		try {
			writer = new PrintWriter("Output.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't open output.");
		}
		try {
			url = new URL(path + pageName);
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL");
		}
        BufferedReader in = null;
        try { in = new BufferedReader(new InputStreamReader(url.openStream())); }
        catch (FileNotFoundException ex) {
        	System.out.println("File not Found.");
        	writer.close();
        	return;
        } catch (IOException e) {
			System.out.println("URLIOEX");
		}
        String inputLine;
        try {
			while ((inputLine = in.readLine()) != null)
			    writer.println(inputLine);
		} catch (IOException e1) {
			System.out.println("readline");
		}
        try {
			in.close();
		} catch (IOException e) {
			System.out.println("closing");
		}
        writer.close();
    }
}