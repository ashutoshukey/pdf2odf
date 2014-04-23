import java.io.IOException;

import org.apache.fontbox.util.ResourceLoader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDGraphicsState;
import org.apache.pdfbox.util.PDFStreamEngine;

public class GetColor {
	public static void main(String[] args) throws IOException {

         PDDocument doc = null;

          try {
        	  doc = PDDocument.load("/home/aashu/pdf2odf/test14.pdf");
        	  PDFStreamEngine engine = new PDFStreamEngine(ResourceLoader.loadProperties("org/apache/pdfbox/resources/PageDrawer.properties"));
        	  PDPage page = (PDPage)doc.getDocumentCatalog().getAllPages().get(0);
        	  engine.processStream(page, page.findResources(), page.getContents().getStream());
        	  PDGraphicsState graphicState = engine.getGraphicsState();
        	  System.out.println(graphicState.getStrokingColor().getColorSpace().getName());
        	  float colorSpaceValues[] = graphicState.getStrokingColor().getColorSpaceValue();
        	  for (float c : colorSpaceValues) {
        		  	System.out.println(c * 255);
        }
      }
         finally {
             if (doc != null) {
                    doc.close();
              }
          }    
      }
}