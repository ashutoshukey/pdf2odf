
class Nodelist {  // like bucket in hash, stores all characters on one line
	float yloc;
	int count;
	boolean isMul;
	//boolean isSparse;
	int sparseCount;
	Nodelist nextNode;
	Nodelist prevNode;
	List listPtr;

Nodelist (float y){ this (y ,false,0, null,null,null);}
Nodelist(float y,boolean mul,int sprse, Nodelist nextnode,Nodelist prevnode,List ptr){
	yloc = y;
	isMul=false;
	sparseCount=0;
	nextNode = nextnode;
	prevNode = prevnode;
	listPtr = ptr;
	count = 0;
}
Object getObject(){ return yloc;}
Nodelist getNextNode(){return nextNode;}
}
class List{                      //node to hold each character and all its information formatting
	String data;
	float fontsize;
	float xloc;
	float width;
	String fontstyle;
	boolean isItalic;
	boolean isBold;
	boolean isSuper;
	boolean isSub;
	List nextItem;
	//List(String item){this (item, null);}
	List(String item,float fsz,float x,float w,String fontstyle,boolean isItalic, boolean Super, boolean Sub,boolean Bold){
		data = item;
		xloc=x;
		width = w;
		fontsize = fsz;
		nextItem = null;
		isSuper = Super;
		isSub=Sub;
		isBold = Bold;
	}
}
public class Node{
	public Nodelist firstNode;  //first bucket
	public Nodelist lastNode;   
	private List currentNode;
	private String name;
	public Node(){this(null);}
	public Node(String listname){
		name = listname;
		firstNode = lastNode = null;
		currentNode = null;
	}
	/*public void insertAtFront( Object insertItem )
	{
		
	if ( isEmpty() ){
	firstNode = lastNode = new Nodelist( insertItem );
	firstNode.listPtr = null;
	}
	else 
	firstNode = new Nodelist( insertItem, firstNode,firstNode.listPtr );
	firstNode.listPtr = null;

	}*/
	public void insertAtBack( float yloc )   // method to create buckets so as to store characters as linked list
	{

		if ( isEmpty() ) {
			firstNode = lastNode = new Nodelist( yloc );
			lastNode.listPtr = null;
		}
		else {
			Nodelist temp = lastNode;
			lastNode=lastNode.nextNode  = new Nodelist( yloc,false,0,null,temp,null);	
			lastNode.listPtr = null;
		}
	}
	public Object removeFromFront() throws EmptyListException // will be used to free the memory
	{
		if ( isEmpty() ) 
			throw new EmptyListException( name );
		Object removedItem = firstNode.yloc; 
		if ( firstNode == lastNode )
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;
return removedItem; 
	} 
	
	public Object removeFromBack() throws EmptyListException  //to free memory
	{
		if ( isEmpty() ) 
			throw new EmptyListException( name );
		Object removedItem = lastNode.yloc; 
		if ( firstNode == lastNode )
			firstNode = lastNode = null;
		else 
		{
			Nodelist current = firstNode;
			while ( current.nextNode != lastNode )
			current = current.nextNode;
			lastNode = current; 
			current.nextNode = null;
		} 
		return removedItem; 
		}
	public boolean isEmpty()
	{
		return firstNode == null; 
	} 
	public void print()       //     to print data structure
	{
		if ( isEmpty() )
		{
			System.out.printf( "Empty %s\n", name );
			return;
		}
		System.out.println( "The page is: " );
		Nodelist current = firstNode;
	// while not at end of list, output current node's data
		while ( current != null )
		{
			System.out.println("        "+current.yloc +" "+current.count + " multiline="+ current.isMul+"Sparse="+current.sparseCount);
			if(current.listPtr == null){
				System.out.println("empty");
				//return;
			}
			List present = current.listPtr;
			while(present != null){
				System.out.printf("%s",present.data);
				present = present.nextItem;
			}
			current = current.nextNode;
		
		}
		System.out.println( "\n" );
		}
	
	// insertInList is method to insert character at current line in data structure
	public void insertInList(String data,float fs,float x,float w,String fstyle,boolean italic,boolean isSuper,boolean isSub,boolean Bold){
		lastNode.count++;
	
		//lastNode.prevNode.isMul =true;
		if(isListEmpty()){
			lastNode.listPtr= new List(data,fs,x,w,fstyle,italic,isSuper,isSub,Bold);
			currentNode = lastNode.listPtr;
		}
		else {
		/*	if( !((int)(currentNode.xloc+currentNode.width) == (int)x 
					|| currentNode.xloc +currentNode.width - x >= 0 || 
					(int)(currentNode.xloc+currentNode.width) - (int)x == -1)) */
			if(x-currentNode.xloc>20.0)
				lastNode.sparseCount++;
			
			currentNode.nextItem = new List(data,fs,x,w,fstyle,italic,isSuper,isSub,Bold);
			currentNode = currentNode.nextItem;
		//	System.out.println("insert at "+lastNode.yloc+" data = "+currentNode.data);

			
		}
	}
	public boolean isListEmpty(){
		return lastNode.listPtr == null;
	}
	
	//insertAt method is to insert character at perticular line in data structure
	public void insertAt(int y,String data,float fs,float x,float w,String fstyle,boolean italic,boolean isSuper,boolean isSub,boolean Bold){
		Nodelist now = firstNode;
		
		while(now != null && (now.yloc!=(float)(y) || now.yloc-(y)>4&&now.yloc-(y)<0 || (y) - now.yloc>4 && (y)-now.yloc<0) ){
			//System.out.println( "finding"+ now.yloc+"  "+y);
			now = now.nextNode;
		}
			if(now.nextNode!=null && now.isMul !=true){
				now.isMul=true;
		//		now.sparseCount++;
			}
		//	System.out.println("qqqqqqqqqqqqqqqqqqqqqqq");
		/*	while(y-now.yloc>3)
			now=now.nextNode;
		if(now.yloc-y>10){
			Nodelist temp = now.nextNode;
			now.nextNode = new Nodelist((int)(y-con),now.nextNode,now,null);
			now = now.nextNode;
			now.nextNode = temp;
insertAt(y, data, fs, x, w, fstyle, italic, con);
			return;
		}*/
			now.count++;
			if(now.listPtr==null){
				now.listPtr= new List(data,fs,x,w,fstyle,italic,isSuper,isSub,Bold);
				currentNode = now.listPtr;
		//		System.out.println("insert at "+now.yloc+" data = "+currentNode.data);

			}
			else {
				List current = now.listPtr;
				while(current.nextItem != null)
					current = current.nextItem;
				if(x-current.xloc>20.0)
		/*		if( !((int)(current.xloc+current.width) == (int)x 
						|| current.xloc +current.width - x >= 0 || 
						(int)(current.xloc+current.width) - (int)x == -1)) */
		//		if((int)(current.xloc+current.width) != x)
					now.sparseCount++;
				
				current.nextItem = new List(data,fs,x,w,fstyle,italic,isSuper,isSub,Bold);
			//	System.out.println("insert at "+now.yloc+" data = "+current.nextItem.data);
				//current = currentNode.nextItem;
		//	insertInList(data, fs, x, w, fstyle, italic);
			}
}
	public boolean isMultiline(float y_start,float y_end){ //used in table detection 
		Nodelist now = firstNode;
		flag = false;
		while(now.nextNode!=null && now.yloc!=y_start)
			now = now.nextNode;
		while(now.nextNode!=null && now.yloc!=y_end){
			if(now.isMul == true)
				flag = true;
				now=now.nextNode;
		}
		return flag;
		
	}
	public int getSparseCount(float y_start, float y_end){ //to get max sparse count in a table 
		Nodelist now = firstNode;
		int max1=0;
		while(now != null && now.yloc!=y_start)
			now = now.nextNode;
		while(now!=null && now.yloc != y_end)
		{
			if(max1<now.sparseCount)
				max1 = now.sparseCount;
			now=now.nextNode;
			
		}
		return max1;
		
	}
	public float isPresent(int y){ //to check if the bucket already exists 
		Nodelist now = firstNode;
	//	boolean flag = false;
		float k=-1;
		if(now == lastNode)
			return k;
		while(now != null){
			if(now.yloc==(float)y || now.yloc-y<4&&now.yloc-y>0 || y - now.yloc<4 && y-now.yloc>0){
		//		flag = true;
				k = now.yloc;
				//System.out.println("Already present  "+now.yloc + "  "+y);
			}
			now=now.nextNode;
		}
		return k;
		}
	public boolean twoColumn(){ //to find if the pdf is 2 column
		Nodelist now = firstNode;
		boolean flag = false;
		while(now.nextNode != null){
			if(now.isMul && now.nextNode.isMul)
				flag = true;
			

			now = now.nextNode;
		}
		return flag;
	}
/*	public void setMul(int y){
		Nodelist now  =firstNode;
		while(now.yloc != y)
			now=now.nextNode;
		now.isMul = true;
	}*/
	static float temp1=-1,temp2;
	static boolean flag = false;
	
	
	public int getSparseLines(float start){ //to decide or detect table on page with its position
		Nodelist now=firstNode;
		while(now!=null && now.yloc != start)
			now = now.nextNode;
		
		while(now !=null && now.nextNode!=null){
			if(now.sparseCount !=0 && now.nextNode !=null&&temp1==-1)
				if( (now.nextNode.sparseCount!=0/*&&now.isMul==false*/)|| (now.nextNode.sparseCount==0&&now.isMul)){
					temp1 =  now.yloc;
					flag = true;
				}
			now = now.nextNode;
		//	if(now.nextNode!=null && now.nextNode.sparseCount==0 && now.isMul ==false)
			//	temp1 = -1;
			if(temp1!=-1&&flag&&(now.sparseCount!=0||now.prevNode.isMul/*||now.count<10*/)){
				
			
						temp2= now.yloc;
						flag = true;
			}
			else
				flag = false;
		}
		if(temp1<0)
			temp1=-temp1;
		if(temp1 < temp2 && (temp2-temp1<30))
			temp1=temp2=-1;
		System.out.println("sssssssssss = "+temp1+"  eeeeeeee"+temp2);
		return (int)(temp1)*1000000+(int)temp2;
	}
	static int max = 0;
	public int chkTable(float y_start,float y_end){ //to confirm table, needed to be improved 
		Nodelist now = firstNode;
		int count=0;

		while(now.nextNode.nextNode!=null && now.yloc!=y_end)
			now=now.nextNode;
		//now=now.nextNode;
	//	List current = now.listPtr;
	/*	while(now.yloc!=y_end){
			if(max<now.sparseCount)
				max = now.sparseCount;
			now = now.nextNode;
		}*/
		max = 10;
			float [] position1 = new float[max+1];
			float [] position2 = new float[max+1];

		//	while(now.yloc!=y_start)
			//	now = now.prevNode;
			//while(now.nextNode.nextNode!=null && now.yloc != y_end ){
			int i=0;
			if(now.prevNode!=null && now.prevNode.isMul==true)
				now = now.nextNode;
				List current = now.listPtr;
			while(i<=max&&current.nextItem!=null){
				position1[i]=current.xloc;
				i++;
				while(current.nextItem!=null && current.nextItem.xloc-current.xloc<15.0)
					current=current.nextItem;
				if(current.nextItem != null)
					current = current.nextItem;
				
			}
			if(now.nextNode!=null)
				now=now.nextNode;
			if(now != null&&now.listPtr!=null)
				current=now.listPtr;
			int j=0;
			if(now.prevNode != null && now.prevNode.isMul==true)
				now = now.nextNode;
			while(j<=max&&current.nextItem!=null){
				position2[j]=current.xloc;
				j++;
				
				while(current.nextItem!=null && current.nextItem.xloc-current.xloc<15.0)
					current=current.nextItem;
				if(current.nextItem!=null)
					current = current.nextItem;

				
			}
			//return (int)position2[3];
			int k=0;
			while(position1[k]!=0){
				if(position1[k]==position2[k])
					count++;
				k++;
			}
			
			//now = now.nextNode;
		//	}
			
			return count;
	}
	static float temp = -1;
	public String getData(float y_present,float y_start,float y_end){ //to get data in a table cell by cell
		String a = "";
		Nodelist now = firstNode;
		
		while(now!=null && now.yloc != y_present && now.listPtr!=null)
			now = now.nextNode;
		//now = now.nextNode.nextNode;
		List	current = now.listPtr;
		if(temp!=-1){
			while(current != null && current.xloc != temp)
				current = current.nextItem;
			if(current.nextItem != null)
				current = current.nextItem;
		}
		float k = -10;
		if(current.nextItem != null)
			k = (current.xloc+current.width) - current.nextItem.xloc;
		System.out.println(k);
		//current = current.nextItem.nextItem.nextItem.nextItem;

		while(current.nextItem!=null && ((int)(current.xloc+current.width) == (int)current.nextItem.xloc 
				|| current.xloc +current.width - current.nextItem.xloc >= 0 || 
				(int)(current.xloc+current.width) - (int)current.nextItem.xloc == -1)){
			a = a+current.data;
		//	System.out.println(a);
			current = current.nextItem;
		
		}
		//if(now.isMul){	}
	//	if(current.nextItem == null)
		//	return "";

		a = a+current.data;
		temp = current.xloc;
		return a;
	}
	public float getNextY(float present){  //to get next y location
		Nodelist now = firstNode;
		while(now != null && now.yloc != present)
			now = now.nextNode;
		if(now.nextNode != null)
			now = now.nextNode;
		return now.yloc;
	}
	public void sort(){
		Nodelist now = firstNode.nextNode, temp1=null,temp2=null;
		while(now != null){
			if(now.yloc < now.prevNode.yloc){
				temp1 = now;
				now.prevNode.nextNode = now.nextNode;
				temp2 = now.prevNode;
				now.nextNode.prevNode = temp2;
				System.out.println("wwwwwwwwwwwwwwwwwwwwww");
				while(now!=null && now.yloc > temp1.yloc)
					now = now.prevNode;
				now = now.prevNode;
				temp1.nextNode = now.nextNode;
				temp1.prevNode = now; 
				now.nextNode = temp1;
				temp1.nextNode.prevNode = temp1;
				now = temp2.nextNode;
			}
			else
				now = now.nextNode;
		}
	}
	public void freePage(){
		Nodelist now = firstNode;
		List current = null;
		List temp;
		if(now.listPtr != null)
			current = now.listPtr;
		while(current.nextItem != null){
			temp = current;
			current = current.nextItem;
			temp = null;
			
		}
			
	}
}