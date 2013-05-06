package Heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HeapSort {

	
	static int [] Heapify(int [] elements,int index,int size){
		
		if(index>=size/2) return elements;

		display_heap(elements);
		
		System.out.println("Heapifying for index: " +index+", element: "+elements[index]);
		int l_index = 2*index + 1;
		int r_index = 2*index + 2;
		
		//int size = elements.length;
		
		int root = elements[index];
		int largest = root;
		int largest_index = index;
		
		if(l_index < size){
			int lchild = elements[l_index];
			if(root<lchild){				
				System.out.println("I'm here!, lchild = "+lchild);
				largest = lchild;
				largest_index = l_index;
			}
		}
		if(r_index < size){
			int rchild = elements[r_index];
			if(largest<rchild){
				System.out.println("I'm here!, rchild = "+rchild);
				largest = rchild;
				largest_index = r_index; 
			}
			
		}
		if(root!=largest){
			elements[index] = largest;
			elements[largest_index] = root;
			System.out.println("Swapping! "+root+" with "+largest+", Calling Heapify idx:"+largest_index+", size:"+size);
			return Heapify(elements,largest_index,size);
		}
		return elements;
			
	}
	
	static int [] Build_heap(int [] elements,int size){
		int len = size;
		for(int j = len/2 -1;j>=0;j--){
			elements = Heapify(elements,j,len);
		}
		return elements;
	}
	
	static int [] H_Sort(int [] elements,int size){
		int [] temp = Build_heap(elements,size);
		for(int j=elements.length-1;j>0;j--){
			int max = temp[0];
			temp[0] = temp[j];
			temp[j]=max;
			size = size-1;
			elements = Heapify(elements,0,size);
		}			
		return elements;
	}
	
	static void display_heap(int [] elements){
		for(int l=0;l<elements.length;l++){
			System.out.print(elements[l] + " ");
		}
		System.out.println(";");
	}
	
	public static void main(String [] args){
		try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String st = br.readLine();
	        String [] chunks = st.split(" ");	        
	        
	        int [] input = new int[chunks.length];
	        
	        for(int i=0;i<chunks.length;i++){
	        	input[i] = Integer.parseInt(chunks[i]);	        	
	        }
	        
	        //System.out.println("I'm here 3!");	              
	        
	        input = H_Sort(input,input.length);
	        for(int k=0;k<input.length;k++){
	        	System.out.print(input[k]+" ");	        
	        }
	    	}
		catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    	}	
	}
}
