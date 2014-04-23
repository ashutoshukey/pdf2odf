
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

public class PrintTextLocations extends PDFTextStripper {
		static int i  = 0;
		static float temp=0;
	public PrintTextLocations() throws IOException {
	    super.setSortByPosition(true);
	}
	static String INPUTFILE ;
	static void setFilename(String name){
		INPUTFILE = name;
	}
	static public String getFilename(){
		return INPUTFILE;
	}
	public static void extract(String filename) throws Exception
	{ 
		setFilename(filename);
	    PDDocument document = null;
	    document = PDDocument.load(filename);
	    PDDocumentInformation pdfinfo = document.getDocumentInformation();
	    List<?> allPages = document.getDocumentCatalog().getAllPages();
	    PrintTextLocations printer = new PrintTextLocations();
	    PDPage page = (PDPage) allPages.get(0);
	    System.out.println("Processing page: "  );
	    PDStream contents = page.getContents();
	    if (contents != null){
	    	printer.processStream(page, page.findResources(), page.getContents().getStream());              
	    }
	    TableDetector.tableRows();
	    System.out.println("Page Height " + page.getMediaBox().getHeight());
	    System.out.println("Page Width " + page.getMediaBox().getWidth());
	    System.out.println( "Page Count = " + document.getNumberOfPages() );
	    System.out.println( "Title = " + pdfinfo.getTitle() );
	    System.out.println( "Author = " + pdfinfo.getAuthor() );
	    System.out.println( "Subject = " + pdfinfo.getSubject() );
	    System.out.println( "Keywords = " + pdfinfo.getKeywords() );
	    System.out.println( "Creator = " + pdfinfo.getCreator() );
	    System.out.println( "Producer = " + pdfinfo.getProducer() );
	    System.out.println( "Creation Date = " + pdfinfo.getCreationDate().toString());
	    System.out.println( "Modification Date = " + pdfinfo.getModificationDate());
	    System.out.println( "Trapped = " + pdfinfo.getTrapped() ); 
	    document.close();
	    PrintImageLocations.main2();
	    PdfImageExtract.main4();
	    TableExtractor.create();
	    TableExtractor.save();
	}
	
	protected void processTextPosition(TextPosition text){      				
			String data;
			char ch;
			boolean isItalic = text.getFont().getFontDescriptor().isItalic();
			float fontsize;
	        float width = text.getWidthDirAdj();
			float Yloc = text.getY();
			float Xloc = text.getX();
			data = text.getCharacter();  
	        ch = data.charAt(0);
			fontsize = text.getFontSizeInPt();
			String fontstyle =text.getFont().getBaseFont();
			if(temp!=Yloc)
			temp = Yloc;
			Boolean Bold=false;
			if(fontstyle.contains("Bold"))
				Bold = true;
			Odfgen.club(data,(int)text.getCharacter().charAt(0) , fontsize   , Yloc , ch , Xloc,width,isItalic,fontstyle,Bold);
			i++;
	}
}