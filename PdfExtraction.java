
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Thomas
 */
public class PdfExtraction {

    /**
     * @param args the command line arguments
     */
	static String str;
	static String str1;
    private static String INPUTFILE = "/home/sagar/saa.pdf"; //Specifying the file location.
   public static void main1() {
        try {
          
        PdfReader reader = new PdfReader(INPUTFILE);
        int n = reader.getNumberOfPages();
        System.out.println(n);
     
  str= PdfTextExtractor.getTextFromPage(reader, 1); 
  String[] s1=str.split("\n");
  for(int k = 0 ; k < s1.length;k++) {
 // System.out.println(s1[k]);
  }
  //System.out.println("Length is" + s1.length);
  int len = s1.length;
  Odfgenerator.createDoc();
  for(int i  = 0 ; i < s1.length; i++) {
	 //.out.println(s1[i]);
	  Odfgenerator.getString(s1[i]);
	  
  }
  //System.out.println(s1[1].length());
  //System.out.println((int)s1[1].charAt(0));
  //System.out.println(s1[1].charAt(0));
  /*for(int i = 0; i < s1[1].length();i++) {
	  System.out.print(s1[2].charAt(i));
	 
  }*/
	  //str1 = PdfTextExtractor.getTextFromPage(reader, 6);(This line is important)           
  //Odfgenerator.getString(str);(this line is important)
  Odfgenerator.main1();
 // System.out.println(str);
        //Document.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
      
    /*
	BufferedWriter writer = null;
	try
	{
		writer = new BufferedWriter( new FileWriter( "/home/sagar/o.odt"));
		writer.write( str);
		//writer.write(str1);(this line is important)

	}
	catch ( IOException e)
	{
	}
	finally
	{
		try
		{
			if ( writer != null)
				writer.close( );
		}
		catch ( IOException e)
		{
		}
    }*/

}}