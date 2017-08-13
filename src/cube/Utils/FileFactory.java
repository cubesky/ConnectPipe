package cube.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileFactory {
	public static List<String> readByLine(String fileName)
			throws FileNotFoundException {
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(new File(fileName));
		List<String> re = new ArrayList<String>();
		while (s.hasNextLine()) {
			String line =s.nextLine().trim();
			if (line.length()>0) {
				re.add(line);
			}
			
		}
		return re;
	}
	/**
	 * 
	 * @param fileName
	 * @param string 
	 * @param begin 锟�锟斤拷锟�?	 * @param end 缁堟锟�?	 * @return
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static BufferedReader readLargeFileByLine(String fileName, String cod) throws FileNotFoundException, UnsupportedEncodingException{
		BufferedReader bfr=null;
		if (cod==null) {
			bfr=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
		}
		else {
			bfr=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),cod));
		}
		return bfr;
		
	}
	public static List<String> readByEncodeLine(String fileName,String encode)
			throws IOException {
		List<String> re = new ArrayList<String>();
		@SuppressWarnings("resource")
		BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)),encode));
		String line=null;
		while ((line=bf.readLine())!=null) {
			line=line.trim();
			if (line.length()>0) {
				re.add(line);		
			}
		
		}
		return re;
	}
	public static void writeFile(String content,String path,boolean append) throws IOException{
		FileWriter fw=new FileWriter(path,append);
		fw.write(content);
		fw.close();
	}
	public static List<String> getAllFileName(String path) {
		File file = new File(path);
		if (file.isFile()) {
			List<String> ls = new ArrayList<String>();
			ls.add(file.getAbsolutePath());
			return ls;
		}
		if (file.isDirectory()) {
			List<String> ls = new ArrayList<String>();
			getAllPath(ls, file);
			return ls;
		}
		return null;
	}

	private static void getAllPath(List<String> ls, File file) {
		if(file.isFile()){
			ls.add(file.getAbsolutePath());
			return;
		}
		else {
			for(File f:file.listFiles()){
				getAllPath(ls, f);
			}
		}
	}
}
