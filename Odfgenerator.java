import java.net.URI;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.doc.office.OdfOfficeText;
import org.odftoolkit.odfdom.doc.style.OdfStyle;
import org.odftoolkit.odfdom.doc.style.OdfStyleTextProperties;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
public class Odfgenerator {
static String s;
static Boolean flag ;
static float rfont;
static float refont;
static int t;
static char c1;
static char c2;
static char c3;
static int z = 0;
static  String count[] = new String[200000];
static float floatsize[] = new float[200000];
static char club[] = new char[200000];
static int inc = 0;
static int p = 10;
static OdfTextDocument outputDocument;
static OdfFileDom contentDom;	
static OdfOfficeText  officeText;

public static void createDoc() {
	try {

		outputDocument = OdfTextDocument.newTextDocument();
		officeText = outputDocument.getContentRoot();
		contentDom = outputDocument.getContentDom();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//style = stylesOfficeStyles.newStyle("sa",
		//	OdfStyleFamily.Paragraph);
}

public static void getCharFont(char a , float fontsize) { 
	
	
	club[z] = a;
	floatsize[z] = fontsize;
	//System.out.print(floatsize[z]);
	z++;
	
}
	public static void getString(String str) {
		//count[inc] = p + "pt";
	//	System.out.println(count[inc]);
		count[inc] = 12 + "pt";
		OdfStyle style;
		OdfOfficeStyles stylesOfficeStyles;
		stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
		s = str;
		//System.out.println(s);
		c1 = s.charAt(0);
		if(s.length() > 1) {
			c2 = s.charAt(1);
		}
		if(s.length() > 2) {
			c3 = s.charAt(2);
	    }
		refont = getFont(c1 , c2 , c3 );
		count[inc] = (int)refont + "pt";
	/*	if(inc > 2) {
			if(count[inc]  == count[inc -1 ]) {
				flag =  false;
			}
			else {
				flag = true;\t
			}
		}
		*/		
		style = stylesOfficeStyles.newStyle("sa" + inc, OdfStyleFamily.Paragraph);
		style.setStyleDisplayNameAttribute("sa" + inc);
		setFontSize(style, count[inc]);
		OdfTextParagraph para = new OdfTextParagraph(contentDom);
		//para.addContentWhitespace("\t");
		para.addStyledContent("sa" + inc , s);
	
			if(t%2!=0) {
				try {
					outputDocument.newParagraph();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		t++;	
		officeText.appendChild(para);
		inc++;
		//p++;
		/*try {
			outputDocument.addText(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
    	/*try {
			outputDocument.newParagraph();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
    public static void main1() {
    /*	OdfTextParagraph para = new OdfTextParagraph(contentDom);
		para.addContent("sagar");
		officeText.appendChild(para);
		*/
		try
		{
			//  outputDocument.newImage(new URI("images/odf-community.jpg"));
			 //outputDocument.newImage(new URI("images/image1.jpg"));
            outputDocument.save("/home/aashu/pdf2odf/linebyline.odt");
		}
		catch (Exception e)
		{
			System.err.println("Unable to create output file.");
			System.err.println(e.getMessage());
		}
    }
    public static void setFontSize(OdfStyleBase style, String value) {
		style.setProperty(OdfStyleTextProperties.FontSize, value);
 		style.setProperty(OdfStyleTextProperties.FontSizeAsian, value);
 		style.setProperty(OdfStyleTextProperties.FontSizeComplex, value);
 	}
    public static float getFont(char c1 , char c2 , char c3 ) {
    	//	System.out.println("Character c2 is" + (int)c2);
    		//System.out.println("char c3 is" + (int)c3);
    		rfont = 12;
    			for(int i = 0 ; i < club.length; i++) {
    				if(((int)c1 == (int)club[i])) {
    					//System.out.println("matched");
    					if((int)c2 == (int)club[i + 1]) {
    						if((int)c3 == (int)club[i + 2]) {
    							 //System.out.println("matched");
    							rfont = floatsize[i];
    							System.out.println("size of " + rfont);
    							return rfont;
    						}
    					}
    				}
    			}
    		
    		
    		//System.out.println(p);
    		return rfont; 
    }
	
	
}