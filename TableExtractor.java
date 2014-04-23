import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.doc.office.OdfOfficeText;
import org.odftoolkit.odfdom.doc.style.OdfStyle;
import org.odftoolkit.odfdom.doc.style.OdfStylePageLayout;
import org.odftoolkit.odfdom.doc.style.OdfStyleTextProperties;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.draw.DrawFrameElement;
import org.odftoolkit.odfdom.dom.element.draw.DrawImageElement;
import org.odftoolkit.odfdom.dom.element.style.StyleMasterPageElement;
import org.odftoolkit.odfdom.dom.element.style.StyleTableCellPropertiesElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;

public class TableExtractor {
	static DrawFrameElement frame;
	static DrawImageElement ima ;
	static OdfTextParagraph next;
static OdfTextDocument outputDocument;
static OdfFileDom contentDom;	
static OdfOfficeText officeText;
	static Node newList; 
	
	public static void create(){
		OdfStyle style;
		OdfStyle style2;
		OdfTextParagraph para;
		Boolean flagtab = false;
		Boolean FlagI = true;
		 try {
				outputDocument =
						OdfTextDocument.newTextDocument();
				 officeText = outputDocument.getContentRoot();
				 StyleMasterPageElement defaultPage = outputDocument.getOfficeMasterStyles ().getMasterPage ( "Standard" );
				 String pageLayoutName = defaultPage.getStylePageLayoutNameAttribute ();
				 OdfStylePageLayout pageLayout = defaultPage.getAutomaticStyles ().getPageLayout ( pageLayoutName );
		 } catch (Exception e1) {
				e1.printStackTrace();
			}
		 try {
				contentDom = outputDocument.getContentDom();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		 OdfOfficeStyles stylesOfficeStyles;
			stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
			 para = new OdfTextParagraph(contentDom);
	        
		 
		String str="";
		float space_width, xmin = 0 ;
		boolean flag = false, flag1 = false;
		newList = Odfgen.list;
		Nodelist now = newList.firstNode;
		List current = now.listPtr;
		if(now.listPtr != null)
			xmin = now.listPtr.xloc;
		int l = 0;
		while(now.nextNode!=null || current.nextItem!= null){
			
			flag = false;
			flag1 = false;
			if(now.yloc == TableDetector.table_start){
				while(now.nextNode != null && now.yloc != TableDetector.table_end)
					now = now.nextNode;
				current = now.listPtr;
			}
			
	        style = stylesOfficeStyles.newStyle("Span" + l,	OdfStyleFamily.Text);
	        style2 = stylesOfficeStyles.newStyle("a" , OdfStyleFamily.Graphic);
	        style.setStyleDisplayNameAttribute("Span" + l);
	        if(current.isItalic)
		        style.setProperty(OdfTextProperties.FontStyle, "italic"); 
	        if(current.isBold)
		        style.setProperty(OdfTextProperties.FontWeight, "bold");

			setFontSize(style,(int)current.fontsize+"pt");
			if(now.listPtr.xloc < xmin)
				xmin = now.listPtr.xloc;
		
			while(now.count>1 && current.nextItem != null && current.nextItem.xloc-(current.xloc+current.width)<=current.fontsize/4 
					&& current.fontsize == current.nextItem.fontsize) {
				
				current = current.nextItem;
		
		}
			l=l+1;
			if(current.nextItem == null && now.nextNode != null){
				now = now.nextNode;
				

				if(now.listPtr != null)
				current = now.listPtr;
				flag = true;
			}

			if(current.nextItem != null && flag == false)
				current = current.nextItem;

			}

		now = newList.firstNode;
		current = now.listPtr;
		l = 0;
		System.out.println("                  "+xmin);
	    xmin = 56;
	    Boolean flag_align = false;
	
		while(now.nextNode!=null || current.nextItem!= null){
			
			space_width = current.fontsize/4;
		/*	if(now.listPtr != null){  for centre alignment some modifications needed
				
				a = now.listPtr;
				start = a.xloc;
				while(a.nextItem != null)
					a = a.nextItem;
				end = a.xloc + a.width;
				if(start + end-595<1){
					para.setProperty (OdfStyleParagraphProperties.TextAlign, "center");
					flag_align = true;
				}
			} */
			if(((now.yloc - PrintImageLocations.fl[0]) < 100)&& FlagI == true && PrintImageLocations.flagimg) {      //Inserting Image.
				float pos1 = PrintImageLocations.xxx[0] - 56;
				float pos = PrintImageLocations.fl[0] - 70;
				float position1 = pos1 /28;
				float position = pos / 28;
				System.out.println(position1);
				String posi = position + "cm";
				String posi1 = position1 + "cm";
				 String width , height;
				  width = PrintImageLocations.widtharray[0] + "cm";
				  height = PrintImageLocations.heightarray[0] + "cm";
				  System.out.println("Height is" + height);
				   next = new OdfTextParagraph(contentDom);
				
				   frame = next.newDrawFrameElement(); 
				   frame.setTextAnchorTypeAttribute("paragraph");
				   frame.setDrawNameAttribute("graphics1");
				   ima = frame.newDrawImageElement();
				   frame.setDrawStyleNameAttribute("s");
				   next.setStyleName("a"); 
				  
				   frame.setSvgHeightAttribute(height);
				   frame.setSvgWidthAttribute(width);
				    frame.setSvgXAttribute(posi1);
				    frame.setSvgYAttribute(posi);
				    ima.setXlinkHrefAttribute(PrintTextLocations.INPUTFILE + "1.jpg");
				    
				    frame.setStyleName("a");
				    
				    
				    ima.setXlinkShowAttribute("embed");
				    ima.setXlinkActuateAttribute("onLoad");
				    ima.setXlinkTypeAttribute("simple");
				    officeText.appendChild(next);
				    FlagI = false;
				
			}
		
			if(now.yloc == TableDetector.table_start){
				
				createTable();

				while(now.nextNode != null && now.yloc != TableDetector.table_end)
					now = now.nextNode;
				if(now.nextNode != null)
				now = now.nextNode;
				if(now.listPtr != null)
				current = now.listPtr;
		
				 para = new OdfTextParagraph(contentDom);
			
				flagtab = true;
				 
			}
			
			if(!flag_align && (now ==newList.firstNode && current == now.listPtr || flag && now.listPtr.xloc > xmin)){
				for(int i=0; i < (now.listPtr.xloc - xmin-30)/space_width;i++){
					para.addStyledContentWhitespace("Span"+l, " ");

					if((now.listPtr.xloc - xmin)/space_width>3){
						para.addStyledContentWhitespace("Span"+l, "  ");
						
					}
				}}
			flag = false;
			flag1 = false;
			flag_align = false;
			while(now.count>1 && current.nextItem != null && current.nextItem.xloc-(current.xloc+current.width)<=current.fontsize/4
					&& current.fontsize == current.nextItem.fontsize) {
				str = str + current.data;
				
				
				
					
				current = current.nextItem;
					

		
		
		}
			str = str + current.data;
			space_width = current.fontsize/4;
			if(current.nextItem != null && (current.nextItem.xloc-(current.xloc+current.width)>space_width)){
				flag1 = true;
		

			}
		
			para.addStyledSpan("Span"+l, str);
			
			if(flag1 && !flag_align)
				for(int i=0; i < (current.nextItem.xloc-(current.xloc+current.width))/space_width;i++){
					para.addStyledContentWhitespace("Span"+l, " ");
					if((current.nextItem.xloc-(current.xloc+current.width))/space_width>3)
						para.addStyledContentWhitespace("Span"+l, "  ");

				

				}
			l=l+1;
			System.out.println(str);
			str = "";
			if(current.nextItem == null && now.nextNode != null){
				now = now.nextNode;
				para.addContentWhitespace("\n");

				if(now.listPtr != null)
				current = now.listPtr;
				flag = true;
			}
		
	
			if(current.nextItem != null && flag == false)
				current = current.nextItem;
			if(!flagtab && now.nextNode != null)
			officeText.appendChild(para);
			else{
				flagtab = false;
				
			}
			}
		} 
	 private static void setFontSize(OdfStyleBase style, String value) {
			style.setProperty(OdfStyleTextProperties.FontSize, value);
			style.setProperty(OdfStyleTextProperties.FontSize, value);
	 		
	 		style.setProperty(OdfStyleTextProperties.FontSizeComplex, value);
	 	}
	 public static void save() {
			try {
				outputDocument.save(PrintTextLocations.INPUTFILE  + ".odt");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	 private static  void createTable(){
		 OdfTable TABLE1 = new OdfTable(contentDom);
		 int i;
		 OdfStyle style;
		 OdfOfficeStyles stylesOfficeStyles;
			stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
		 style = stylesOfficeStyles.newStyle("Span",	OdfStyleFamily.Table);
		 style.setProperty(StyleTableCellPropertiesElement.BorderLineWidth, "10");
		 style.setProperty(StyleTableCellPropertiesElement.BackgroundColor, "yellow");
		
		 
		 TABLE1.setStyleName("Span");
		 OdfTableRow row;
		 OdfTableCell cell;
		 OdfTextParagraph parag1;
		 String str="";
		 newList = Odfgen.list;
		 Nodelist now = newList.firstNode;
		 List current;
		 while(now.yloc != TableDetector.table_start)
			 now = now.nextNode;
		 current = now.listPtr;
		 for(i=0;i<=newList.getSparseCount(TableDetector.table_start, TableDetector.table_end);i++)

		
			 	TABLE1.addTableColumn();
		 while(now.yloc!= TableDetector.table_end){
			 row = new OdfTableRow(contentDom);
 
		 
			 while(current!=null){
				 cell = new OdfTableCell(contentDom);
				 while(current.nextItem!=null && current.nextItem.xloc-current.xloc<15&& ((int)(current.xloc+current.width) == (int)current.nextItem.xloc 
							|| current.xloc +current.width - current.nextItem.xloc >= 0 || 
							(int)(current.xloc+current.width) - (int)current.nextItem.xloc == -1 || 
							current.nextItem.xloc-current.xloc<9)){
				
					 str = str + current.data;
					current = current.nextItem;
				
				 }
				 str = str+current.data;
				 System.out.println(str);
				 current = current.nextItem;
		
				 parag1 = new OdfTextParagraph(contentDom , null , str);
				 cell.appendChild(parag1);
				 row.appendCell(cell);
				 str = "";

				// str = str +"a";
			 }
			 TABLE1.appendRow(row);
			 now = now.nextNode;
			 current = now.listPtr;
		 }
		 row = new OdfTableRow(contentDom);
		 
		 
		 while(current!=null && current.nextItem!=null){
			 cell = new OdfTableCell(contentDom);
			 while(current.nextItem!=null && ((int)(current.xloc+current.width) == (int)current.nextItem.xloc 
						|| current.xloc +current.width - current.nextItem.xloc >= 0 || 
						(int)(current.xloc+current.width) - (int)current.nextItem.xloc == -1)){
				 str = str + current.data;
				current = current.nextItem;
			}
			 str = str+current.data;
			 System.out.println(str);
			 current = current.nextItem;
	
			 parag1 = new OdfTextParagraph(contentDom , null , str);
			 cell.appendChild(parag1);
			 row.appendCell(cell);
			 str = "";

			
		 }
		 TABLE1.appendRow(row);
		
		 officeText.appendChild(TABLE1);
	

	 }
}