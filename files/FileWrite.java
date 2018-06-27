package files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileWrite {

   public FileWrite(String messages[],int max){      
      File f = null;
      boolean bool = false;
      BufferedWriter bw = null;
      
      
      try {
      
         // create new file
         f = new File("messages.txt");
        
         
         // tries to create new file in the system
         bool = f.createNewFile();
         
         FileWriter fw = new FileWriter("messages.txt");
         bw = new BufferedWriter(fw);
         
         
         // prints
         System.out.println("max in file: "+ max);
         for(int i=0; i<max;i++) {
        	 bw.write(messages[i]);
        	 bw.newLine();
         }
         bw.close();
         // deletes file from the system
        //  f.delete();
         
         // delete() is invoked
         System.out.println("delete() method is invoked");
         
         // tries to create new file in the system
       //  bool = f.createNewFile();
         
         // print
        // System.out.println("File created: "+bool);
            
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
}