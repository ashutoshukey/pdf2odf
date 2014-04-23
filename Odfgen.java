public class Odfgen {


	
	static int i = 0;
	static int j;
	static String str = "s";
	static int prev = 0;
	public static Node list = new Node();
	static float con;
	static boolean flg=false;
	static float temp =-1;
	static boolean tmp1=false;
	public static void club(String data ,int intchar, float fontsize , float yloc , char ch , float xloc, float width,boolean italic,
			String fontstyle, boolean Bold) {
	if(temp==-1 && intchar == 32){
		return;
	}
	if(temp == -1 ){
			temp = yloc;
			con = temp;
		list.insertAtBack(0);
		}
		tmp1=false;
		boolean isSuper=false;
		if((int)(yloc-temp)==-4)
			isSuper = true;
		boolean isSub=false;
		if((int)(yloc-temp)<4 && yloc-temp > 0.0)
			isSub = true;
		if(list.isPresent((int)(yloc))!=-1){
			float k = (float)list.isPresent((int)(yloc));
		
			list.insertAt((int)k,data, fontsize, xloc, width, fontstyle,italic,isSuper,isSub,Bold);
		
			tmp1=true;
			
			}
		if(!tmp1){
	
		if( yloc - temp>5){
			
			list.insertAtBack((int)(yloc));

		}
		
		
		list.insertInList(data, fontsize, xloc, width, fontstyle,italic,isSuper,isSub,Bold);
		}
		temp = yloc;
			i++;
		
		
	}
}

