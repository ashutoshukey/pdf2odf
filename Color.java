import java.io.IOException;

import org.apache.fontbox.util.ResourceLoader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDGraphicsState;
import org.apache.pdfbox.util.PDFStreamEngine;


public class Color {
	 static PDFStreamEngine engine;
	public static void main(String [] args) throws IOException {
	PDDocument doc = null;
	//PrintTextLocations printer = new PrintTextLocations();
	
    
	try {
	    try {
			doc = PDDocument.load("/home/aashu/pdf2odf/test14.pdf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    PDFStreamEngine engine = new PDFStreamEngine(ResourceLoader.loadProperties("org/apache/pdfbox/resources/PageDrawer.properties"));
		PDPage page = (PDPage)doc.getDocumentCatalog().getAllPages().get(0);
	    try {
			engine.processStream(page, page.findResources(), page.getContents().getStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    PDGraphicsState graphicState = engine.getGraphicsState();
	    System.out.println(graphicState.getStrokingColor().getColorSpace().getName());
	    float colorSpaceValues[] = graphicState.getStrokingColor().getColorSpaceValue();
	    for (float c : colorSpaceValues) {
	        System.out.println(c * 255);
	    }
	}
	finally {
	    if (doc != null) {
	        try {
				doc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}}}
