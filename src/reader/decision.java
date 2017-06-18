package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;

public class decision {
	
	
	
	
	static int[] getValues(String csvFile)
	{
	
		 try{
	         BufferedReader br = new BufferedReader(new FileReader(csvFile));
	         String line = "";
	         StringTokenizer st = null;

	         int[] count=new int[2];
	         count[0]=0;
	         count[1]=0;
	         
	    	 
	         if ((line = br.readLine()) != null) {
	             count[1]++;

	             //use comma as token separator
	             st = new StringTokenizer(line, ",");
	           
	             while (st.hasMoreTokens()) 
	              { st.nextToken();
	               count[0]++;
	               }
	           
	         
	           }
	         
	         while ((line = br.readLine()) != null) 
	             count[1]++;

	         count[1]--;
	         br.close();
	         return count;
	         
	    	 }
	    	 catch (Exception e)
	    	     {
	    	       System.err.println("CSV file cannot be read : " + e);
	    	     }
		return null;
		
		 
	}
	
	 public static void main(String[] args) throws IOException
	{
	String[] file=new String[3];
	boolean printFlag=false;
	int l,k;
	BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("enter the values of l and k");
	l=Integer.parseInt(bufferRead.readLine());
	k=Integer.parseInt(bufferRead.readLine());
	//String s1 =  "E:\\assignment1\\training_set.csv";
	//String s2 =  "E:\\assignment1\\validation_set.csv";
	//String s3 =  "E:\\assignment1\\test_set.csv";
	System.out.println("enter the file paths");
    String csvFile = bufferRead.readLine();
    Set trainingSet=new Set();
    int[] count=getValues(csvFile);
    if(count==null)
    	{System.out.println("empty data");
    	 
    	}
    trainingSet.attribCount=count[0];
    trainingSet.example=count[1];
    trainingSet.attribValues=new int[trainingSet.example][trainingSet.attribCount];
    trainingSet.attributes=new String[count[0]];
    trainingSet.addvalues(csvFile);
    
    
	csvFile = bufferRead.readLine();
	
	Set validatingSet=new Set();
   count=getValues(csvFile);
   if(count==null)
   	{System.out.println("empty data");
   	}
   validatingSet.attribCount=count[0];
   validatingSet.example=count[1];
   validatingSet.attribValues=new int[validatingSet.example][validatingSet.attribCount];
   validatingSet.attributes=new String[count[0]];
   validatingSet.addvalues(csvFile);
   validatingSet.print();
   
   csvFile = bufferRead.readLine();
   Set testSet=new Set();
   count=getValues(csvFile);
	   if(count==null)
	   	{System.out.println("empty data");
	   	 
	   	}
	    testSet.attribCount=count[0];
	    testSet.example=count[1];
	    testSet.attribValues=new int[testSet.example][testSet.attribCount];
	    testSet.attributes=new String[count[0]];
	    testSet.addvalues(csvFile);
	 
System.out.println("enter to print Flag");
csvFile=bufferRead.readLine();
if(csvFile.equalsIgnoreCase("yes"))
		printFlag=true;
else if(csvFile.equalsIgnoreCase("NO"))
			printFlag=false;
	else System.out.println("invalid string");
	
    node n=new node();
    list nodeBank;

    node[] root=new node[2];
    root[0]=new node();
    Tree dTree1=new Tree();
    dTree1.nodeBank.add(null);
    dTree1.growTree(root[0], trainingSet,false);
    dTree1.printTree(0,1, trainingSet.attributes);
    validatingSet.Prune(dTree1,l,k);
    dTree1.getaccuracy(testSet);
    
    System.out.println();
    System.out.println();
     Tree dTree2=new Tree();
      nodeBank=dTree2.growTree(root[0], trainingSet,true);
      dTree2.printTree(0,1, trainingSet.attributes);
      validatingSet.Prune(dTree2,l,k);
      dTree2.getaccuracy(testSet);


}
}