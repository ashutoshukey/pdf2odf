// Import statements
import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;





import java.util.regex.*;

public class PDFtest1 {
 public static void main(String[] args){
 PDDocument pd;
 try {
         //  PDF file from the phone numbers are extracted
         File input = new File("/home/sagar/a.pdf");

         // StringBuilder to store the extracted text
         StringBuilder sb = new StringBuilder();
         pd = PDDocument.load(input);
         PDFTextStripper stripper = new PDFTextStripper();
         String a = stripper.getText(pd);
         
         // Add text to the StringBuilder from the PDF
         sb.append(stripper.getText(pd));
         //System.out.println(a);
        String[] s1 = a.split("/n");
        for(int k = 0 ; k < s1.length;k++) {
        	  System.out.println(s1[k]);
        	  }
        int len = s1.length;
        System.out.println("len is" + s1.length);
        //Odfgenerator.createDoc();
        for(int i  = 0 ; i < len; i++) {
    	  System.out.println(s1[i]);
      //	  Odfgenerator.getString(s1[i]);
      	  
        }
    //    Odfgenerator.main1();

         // Regex. For those who do not know. The Pattern refers to the format you are looking for.
         // In our example, we are looking for numbers with 10 digits with atleast one surrounding whitespaces
         // on both ends.
         //Pattern p = Pattern.compile("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");

         // Matcher refers to the actual text where the pattern will be found
         //Matcher m = p.matcher(sb);

         //while (m.find()){
             // group() method refers to the next number that follows the pattern we have specified.
           //  System.out.println(m.group());
         //}

         if (pd != null) {
             pd.close();
         }
 } catch (Exception e){
         e.printStackTrace();
        }
     }
} 