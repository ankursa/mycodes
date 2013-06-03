package stringDemo.palindrome;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindrome_split { //Merging two sorted arrays
	
	String input;
	int splits;
	
	int [][] cuts_arr;
	
	public Palindrome_split() {
	}
	
	public boolean is_palindrome(String st){
		//String st = this.input;
		int len = st.length();
		for(int i=0;i<=len/2;i++){
			if(st.charAt(i)!=st.charAt(len-i-1)){
				//System.out.println("comparing failed on index -" + i + ", chars =" + st.charAt(i) + " " + st.charAt(len-i-1));
				return false;
			}
		}
		return true;
	}
	
	public int min_palindromes(String st, int count){
		//String st = this.input;
		int c_update = count;
		System.out.println("Counting for string: " + st + ", Current count =" + count);
		if(st.length()==1 || is_palindrome(st)){
			//this.splits++;
			return c_update++;
		}		
		c_update = Math.min(min_palindromes(st.substring(1),count+1), min_palindromes(st.substring(0,st.length()-1),count+1));
		return c_update;
		//return this.splits;
	}
	
	int min_cuts(String st){
		if(st.length()<=1 || is_palindrome(st)){
			return 0;
		}
		int len = st.length();
		int temp_min;
		int global_min = len-1;
		//String subst1 = new String();
		//String subst2 = new String();
		String subst1,subst2;
		for(int i=1;i<len;i++){
			subst1 = st.substring(0, i);
			subst2 = st.substring(i, len);
			temp_min = min_cuts(subst1)+min_cuts(subst2)+1; //these cuts are repeatedly calculated numerous times. Avoid this by going bottom up and saving values of min_cuts of smaller strings and use it for larger strings.
			if(temp_min<global_min){
				global_min = temp_min;
			}
		}
		return global_min;
	}
	
	int min_cuts2(String st){
		
		int len = st.length();
		int [][] cuts_arr = new int[len][len];
			
		
		for(int l=0;l<=len;l++){
			int min_cuts2 =l;
			int tmp = l;
			for(int ind=0;ind<len-l;ind++){
				System.out.println(st.substring(ind, ind+l+1));
				if(this.is_palindrome(st.substring(ind,ind+l+1))){					
					min_cuts2 = 0;
					cuts_arr[ind][ind+l]=min_cuts2;
					continue;
				}
				
				for(int j=1;j<l;j++){
					tmp=1+cuts_lookup(cuts_arr,ind,ind+j)+cuts_lookup(cuts_arr,ind+j+1,l);					
					if(tmp<min_cuts2){
						min_cuts2 = tmp;
					}
				}
				
				cuts_arr[ind][ind+l]=min_cuts2;
			}
		}
		this.print_2darr(cuts_arr);
		return cuts_arr[0][len-1];
	}
	
	int cuts_lookup(int [][] cuts_arr,int start, int end){
		return cuts_arr[start][end];
	}

	void print_2darr(int [][] cuts_arr){
		for(int i=0;i<cuts_arr.length;i++){
			for(int j=0;j<cuts_arr[0].length;j++){
				System.out.print(cuts_arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args){
		try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        Palindrome_split ps = new Palindrome_split();
	        ps.input = br.readLine();
	        //aSystem.out.println(ps.input.substring(1) + " " + ps.input.substring(0,ps.input.length()-1));
	        if(ps.is_palindrome(ps.input))
	        	System.out.println("Test passed");
	        else System.out.println("Not a palindrome");
	        //System.out.println(ps.min_palindromes(ps.input,0));
	        System.out.println(ps.min_cuts(ps.input));
	        System.out.println(ps.min_cuts2(ps.input));
	        //System.out.println(ps.input.substring(0,1));
	    	}
		catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    	}		
	}
}
