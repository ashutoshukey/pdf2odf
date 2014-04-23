
public class TableDetector {
	static Node nList;
	static float table_start=-1,table_end=-1;
	static 	boolean mul;
	static 	int cnt=0;
	static int sparse;

	public static void tableRows( ){
		nList = Odfgen.list;
	
		int a = nList.getSparseLines(0);
		
		if(a!=1000000){
		table_start=(int)a/1000000;
		table_end=a%1000000;
		
		tableConf();

		}
	}

	public static void tableConf(){
		nList=Odfgen.list;
		boolean flag = nList.twoColumn();
		if(flag){
			System.out.println("This is two column PDF");
			table_start = table_end =-1;
		}
		if(table_start!=-1&&table_end!=-1 && flag==false){
			cnt = nList.chkTable(table_start,table_end);
		mul = nList.isMultiline(table_start, table_end);
		sparse = nList.getSparseCount(table_start,table_end);
		float P = tableProbability();
		System.out.println("table starts at:  "+table_start + "   table ends at:   "+table_end);
		System.out.println("Probability of table on this page is: "+P);
	
		Nodelist now = nList.firstNode;
		while(now.yloc != table_start)
			now = now.nextNode;
		float max_cnt= now.count;
		while(now.yloc != table_end){
			if(max_cnt < now.count)
				max_cnt = now.count;
			now = now.nextNode;
		}
		System.out.println("     max cnt      "+max_cnt);
		if(max_cnt >50 && !mul && nList.getSparseCount(table_start, table_end)<2){
			table_start = table_end=-1;
			P = P/2;
			System.out.println("     not a table");

		}
		}
	}
	public static float tableProbability(){
		int points=0,max=25;
		if(table_start!=-1 && table_end != -1){
			points = points+24;
		
			if(mul){
				points =points+ 30;
				max =max+ 30;
	

			}
			if(sparse<cnt)
			points =points + 5-(cnt-1-sparse);
			max = max + 5;
		}
	
			return (float)points/max;	
		
		
	}

}
