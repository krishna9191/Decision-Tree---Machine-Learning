package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Set {

	
	int attribCount,example;
	int[][]attribValues;
	String[] attributes;
	public void addvalues(String csvFile) {
		try{
	         BufferedReader br = new BufferedReader(new FileReader(csvFile));
	         String line = "";
	         StringTokenizer st = null;

	         int lineNumber = 0; 
	         int tokenNumber = 0;
	    	 
	         if ((line = br.readLine()) != null) {
	             lineNumber++;

	             //use comma as token separator
	             st = new StringTokenizer(line, ",");
	           
	             while (st.hasMoreTokens()) {
	               tokenNumber++;

	            this.attributes[tokenNumber-1]=st.nextToken();
	             
	             }
	             //reset token number
	             tokenNumber = 0;
	           }
	         
	         while ((line = br.readLine()) != null) {
	             lineNumber++;

	             //use comma as token separator
	             st = new StringTokenizer(line, ",");
	        
	             while (st.hasMoreTokens()) {
	               tokenNumber++;

	             this.attribValues[lineNumber-2][tokenNumber-1]=Integer.parseInt(st.nextToken());
	              
	             }

	             tokenNumber = 0;
	           }

	         br.close();
	         
	    	 }
	    	 catch (Exception e)
	    	     {
	    	       System.err.println("CSV file cannot be read : " + e);
	    	     }
		
	}
	public void print() {
		for(int i=0;i<example;i++)
			{for(int j=0;j<attribCount;j++)
				System.out.print(attribValues[i][j]);
			System.out.println("      "+i);
			}
		
	}
	public Tree Prune(Tree dTree1, int l, int k) {
		Tree dBest=dTree1;
		double bestAccuracy=dBest.getaccuracy(this);
		double accuracy=0;
		for(int i=0;1<l;i++)
		{
			Tree dPrime=dTree1;
			Random m=new Random();
			int m1=m.nextInt(k);
			for(int j=0;j<m1;j++)
				{int n=leafNodes(dPrime);
					Random p=new Random();
					int p1=p.nextInt(n);
					dPrime.remove(p1);
				}
			accuracy=dPrime.getaccuracy(this);
			if(accuracy>bestAccuracy)
				dBest=dPrime;
			
		}
		return dBest;
	}
	private int leafNodes(Tree dPrime) {
		int count=0;
		for(int i=0;dPrime.nodeBank.get(i)!=null;i++)
			if(dPrime.nodeBank.get(i).attribute!=-1)
				count++;
				
		return count;
	}
	
	
	
	
	
}
