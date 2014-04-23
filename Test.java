
import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.doc.office.OdfOfficeText;
import org.odftoolkit.odfdom.doc.style.OdfDefaultStyle;
import org.odftoolkit.odfdom.doc.style.OdfStyle;
import org.odftoolkit.odfdom.doc.style.OdfStyleParagraphProperties;
import org.odftoolkit.odfdom.doc.style.OdfStyleTabStop;
import org.odftoolkit.odfdom.doc.style.OdfStyleTabStops;
import org.odftoolkit.odfdom.doc.style.OdfStyleTextProperties;
import org.odftoolkit.odfdom.doc.text.OdfTextAlphabeticalIndex;
import org.odftoolkit.odfdom.doc.text.OdfTextHeading;
import org.odftoolkit.odfdom.doc.text.OdfTextList;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.doc.text.OdfTextSpan;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfStyleProperty;
import org.odftoolkit.odfdom.type.Color;
import org.odftoolkit.odfdom.type.Language;
import org.odftoolkit.simple.*;
import org.odftoolkit.simple.style.Font;
import org.odftoolkit.simple.style.StyleTypeDefinitions;
import org.w3c.dom.Node;

import java.net.URI;
import java.util.Locale;
public class Test {
	static OdfTextDocument outputDocument;
	static OdfFileDom contentDom;	
	static OdfOfficeText officeText;
	public static void main(String [] args) {
	try {
		outputDocument =
				OdfTextDocument.newTextDocument();
		 officeText = outputDocument.getContentRoot();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    OdfDefaultStyle defaultStyle;
	OdfStyle style;
	OdfStyleParagraphProperties pProperties;
	OdfTextAlphabeticalIndex alpha;
	OdfTextParagraph a , next , para;
	OdfOfficeText p;
	OdfTextList c;
	OdfTextHeading heading;
	OdfTextSpan stars;
	OdfStyleTabStops tabStops;
	try {
		contentDom = outputDocument.getContentDom();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	OdfStyleTabStop tabStop;
	OdfOfficeStyles stylesOfficeStyles;
	stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
    defaultStyle = stylesOfficeStyles.getDefaultStyle( OdfStyleFamily.Paragraph);
    style = stylesOfficeStyles.newStyle("sagar",	OdfStyleFamily.Paragraph);
   // style.setStyleDisplayNameAttribute("sagar");
    
   // para.addContent("qwq");
   // para.addContent("sasa");
    style.setProperty(OdfStyleParagraphProperties.TextAlign, "center");
    
    para = new OdfTextParagraph(contentDom);
    
    //style.setProperty(OdfStyleTextProperties.TextPosition, "center");
    para.addStyledContent( "sagar" , "sagaraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    officeText.appendChild(para);
    try {
		outputDocument.save("/home/sagar/a.odt");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
	
	
	

}
