package files;

import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class FileAccess {
	//Map<String,Integer> wordMap=new HashMap<String,Integer>();
	
	public FileAccess(){
		super();
		
			File file=null;
			Scanner sc=null;
			try {
				file=new File("C:\\Users\\haris_lnf3mxz\\Desktop\\BOOTCAMP\\git\\15.txt");
				sc = new Scanner(file);
				
			} catch (Exception ex) {
				System.out.println("test file ");
			} 
			
			String test1=sc.next();
			System.out.println(test1); 
			
	}
	
	
}
