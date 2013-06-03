import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Reverse_kNodes extends LList {
	
	public Reverse_kNodes(String [] input){
		super(input);
	}

	void iterateNreverseK(int k){
		if(k<=0) return;
		Node current = this.head;
		Result r = this.reverse_segment(current, k);		
		this.head = r.start;
		//this.LL_print();
		Node prev_tail = r.end;
		current = r.end.next;
		while(current!=null){
			r = this.reverse_segment(current, k);
			prev_tail.next = r.start;
			current = r.end.next;
			prev_tail = r.end;
		}
		prev_tail.next = null;
	}
	
	void reverse_list(){
		Node start = this.head;
		Node tmp = this.head.next;
		Node tmp2 = this.head;
		tmp2.next = null;
		while(tmp.next!=null){
			tmp2 = tmp;
			//tmp2.next = null;
			tmp = tmp.next;			
			tmp2.next = start;
			start = tmp2;					
		}		
		tmp.next = start;
		this.head = tmp;		
	}
	
	Result reverse_segment(Node start,int k){
		if(start.next==null){
			Result r = new Result(start,start);
			return r;
		}
		
		Node seg_head = start;
		Node seg_tail = start;
		//tail.next=null;
		Node tmp = start.next;
		Node tmp2 = start;
		
		for(int i=1;i<k-1;i++){
			if(tmp.next!=null){
				//tmp=tmp.next;
				//tail.next = tmp.next;				
				tmp2 = tmp;
				tmp = tmp.next;
				tmp2.next = seg_head;
				seg_head = tmp2;
				//tmp = tmp.next;				
				//System.out.println("Here: seg_head: "+seg_head.key+", seg_tail: "+seg_tail.key+", tmp: "+tmp.key);				
			}
			else break;
		}
		System.out.println("test3");
		seg_tail.next = tmp.next;
		tmp.next = seg_head;
		seg_head = tmp;
		
		//tail.next = null;
		
		Result r = new Result(seg_head,seg_tail);
		System.out.println(r.start.key+" "+r.end.key);
		return r;		
	}
	
	public static void main(String [] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String st = br.readLine();
	        String [] chunks = st.split(" ");
	        //LList l = new LList(chunks);
	        Reverse_kNodes rkn = new Reverse_kNodes(chunks);
	        rkn.LL_print();
	        int k = Integer.parseInt(br.readLine());
	        rkn.iterateNreverseK(k);
	        //rkn.reverse_list();
	        rkn.LL_print();
		}
		catch(Exception e){
			System.err.println("Error: "+e.getMessage());
		}
	}
	
}

class Result{
	Node start;
	Node end;
	
	Result(Node head,Node tail){
		this.start = head;
		this.end = tail;
	}
}
