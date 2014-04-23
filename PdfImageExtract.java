import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;




public class PdfImageExtract {
	public static void main4() {
		   PdfImageExtract obj = new PdfImageExtract();
		    try {
		        obj.read_pdf();
		    } catch (IOException ex) {
		        System.out.println("" + ex);
		    }

		}

		 void read_pdf() throws IOException {
		    PDDocument document = null; 
		    try {
		        document = PDDocument.load(PrintTextLocations.INPUTFILE);
		    } catch (IOException ex) {
		        System.out.println("" + ex);
		    }
		    List pages = document.getDocumentCatalog().getAllPages();
		    Iterator iter = pages.iterator(); 
		    int i =1;
		    String name = null;

		    while (iter.hasNext()) {
		        PDPage page = (PDPage) iter.next();
		        PDResources resources = page.getResources();
		        Map pageImages = resources.getImages();
		        if (pageImages != null) { 
		            Iterator imageIter = pageImages.keySet().iterator();
		            while (imageIter.hasNext()) {
		                String key = (String) imageIter.next();
		                PDXObjectImage image = (PDXObjectImage) pageImages.get(key);
		                image.write2file( PrintTextLocations.INPUTFILE + i );
		                i ++;
		            }
		        }
		    }

		  }
		 
		 
		 
		 
		 }