package reader;


public class node {
	
	node left=null;
	node right=null;
	node parent=null;
	boolean visited=false;
	int attribute=-1;
	int label=-1;
	int lIndex=-1;
	int rIndex=-1;
	int pIndex=-1;

	private int[] attributeIndex={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	public node getParent() {
		return parent;
	}
	public void setParent(node parent) {
		this.parent = parent;
	}
	public int getAttribute() {
		return attribute;
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public node getLeft() {
		return left;
	}
	public void setLeft(node left) {
		this.left = left;
	}
	public node getRight() {
		return right;
	}
	public void setRight(node right) {
		this.right = right;
	}
	public void setAttributeIndex(int[] attributeIndex) {
		this.attributeIndex = attributeIndex;
	}
	public int[] getAttributeIndex() {
		return attributeIndex;
	}
	
	
	static int[] attributeIndexSetFalse(int attributeIndex[], int attributeId)
	{
		int[] attributeIndex2=new int[attributeIndex.length];
		for(int i=0;i<attributeIndex.length;i++)
			attributeIndex2[i]=attributeIndex[i];
		attributeIndex2[attributeId]=0;
		return attributeIndex2;
	}
	 static int[] attributeIndexSetTrue(int attributeIndex[],int attributeId)
		{	
			int[] attributeIndex2=new int[attributeIndex.length];
			for(int i=0;i<attributeIndex.length;i++)
				attributeIndex2[i]=attributeIndex[i];
			attributeIndex2[attributeId]=1;
			return attributeIndex2;
		}	
	  int[] print(int[] attributeIndex2) {
		for(int i=0;i<21;i++)
			if(attributeIndex2[i]==-1)
				System.out.print("x"+"  ");
			else
				System.out.print(attributeIndex2[i]+"  ");
		System.out.println();
		return attributeIndex2;
		
		
	}
	 double mod(int attributeIndex[], Set trainingSet)
		{	double pPlus,pNeg;
			pNeg=this.getPNeg(attributeIndex, trainingSet);
			pPlus=this.getPPlus(attributeIndex, trainingSet);
			return (pPlus+pNeg);
		}
	 double[] findGain(Set trainingSet, int[] attributeIndex, int attributeId, boolean gainFlag)
	{
		double[] gainS=new double[7];
		double s1,s2,s3;
		int[] attributeIndex2=new int[attributeIndex.length];
		for(int i=0;i<attributeIndex.length;i++)
			attributeIndex2[i]=attributeIndex[i];
			if(!gainFlag)
			{	
				s1=mod(attributeIndexSetTrue(attributeIndex,attributeId),trainingSet );
				s2=mod(attributeIndexSetFalse(attributeIndex,attributeId),trainingSet );
				s3=s2+s1;
				if(s3!=0)
					{	gainS[2]= entropy(attributeIndex, trainingSet);
						gainS[1]=entropy(attributeIndexSetTrue(attributeIndex,attributeId), trainingSet);
						gainS[0]=entropy(attributeIndexSetFalse(attributeIndex,attributeId), trainingSet);
						gainS[2]= gainS[2]-(s1/s3 )*gainS[1] -(s2/s3)*gainS[0];
						gainS[3]=s1;
						gainS[4]=s2;
					}
				else
						
						{gainS[2]=entropy(attributeIndex, trainingSet);
						gainS[3]=s1;
						gainS[4]=s2;
						}
				
				}
			else
			{
				s1=mod(attributeIndexSetTrue(attributeIndex,attributeId),trainingSet );
				s2=mod(attributeIndexSetFalse(attributeIndex,attributeId),trainingSet );
				s3=s2+s1;
				if(s3!=0)
				{	gainS[2]= VI(attributeIndex, trainingSet);
					gainS[1]=VI(attributeIndexSetTrue(attributeIndex,attributeId), trainingSet);
					gainS[0]=VI(attributeIndexSetFalse(attributeIndex,attributeId), trainingSet);
					gainS[2]= gainS[2]-(s1/s3 )*gainS[1] -(s2/s3)*gainS[0];
					gainS[3]=s1;
					gainS[4]=s2;
				}
			else
					
					{gainS[2]=VI(attributeIndex, trainingSet);
					gainS[3]=s1;
					gainS[4]=s2;
					}
				
			}
			
			for(int i=0;i<attributeIndex.length;i++)
				attributeIndex[i]=attributeIndex2[i];
		return gainS;
		
		
	}
	 private double VI(int[] attributeIndexSetTrue, Set trainingSet) {
		 double pPlus=0, pNeg=0;
			double e=0;
			pPlus=getPPlus(attributeIndex,trainingSet);
			pNeg=getPNeg(attributeIndex,trainingSet);
			if(pPlus==0&&pNeg==0);
			else
				{e=pPlus;
			pPlus=pPlus/(pPlus+pNeg);
			pNeg=pNeg/(e+pNeg);
				}
			e=(pPlus*pNeg);
			return(e);
	}
	double entropy(int attributeIndex[],Set trainingSet)
	{
		double pPlus=0, pNeg=0;
		double e=0;
		
		pPlus=getPPlus(attributeIndex,trainingSet);
		pNeg=getPNeg(attributeIndex,trainingSet);
		if(pPlus==0&&pNeg==0);
		else
			{e=pPlus;
		pPlus=pPlus/(pPlus+pNeg);
		pNeg=pNeg/(e+pNeg);
			}
		if(pPlus==0||pNeg==0)
			e=0;
		else
			e=-(pPlus*log2(pPlus))-(pNeg*log2(pNeg));
		return(e);
	}
	  int getPNeg(int[] attributeIndex2, Set trainingSet) {
		int pNeg=0,i=0,j=0,count=0;
		
		for(i=0;i<trainingSet.example;i++)
		{
			for(j=0,count=0;j<trainingSet.attribCount-1;j++)
			{
				if(attributeIndex2[j]!=-1)
					{if(trainingSet.attribValues[i][j]==attributeIndex2[j])
						{count++;
						}
					}
				else
					count++;
			}
			if(count==trainingSet.attribCount-1)
			{
				if(trainingSet.attribValues[i][j]==0)
					pNeg++;
			}
		}
		return pNeg;
	}
	int getPPlus(int[] attributeIndex2, Set trainingSet) {
		int pPlus=0,i=0,j=0,count=0;
		for(i=0;i<trainingSet.example;i++)
		{
			for(j=0,count=0;j<trainingSet.attribCount-1;j++)
			{
				if(attributeIndex2[j]!=-1)
					{if(trainingSet.attribValues[i][j]==attributeIndex2[j])
						{count++;
						}
					}
				else
					count++;
					
					
					
			}
			if(count==trainingSet.attribCount-1)
			{
				if(trainingSet.attribValues[i][j]==1)
					pPlus++;
			}
		}
		return pPlus;
	}
	double log2(double i)
	{	if(i==1)
			return 0;
		else
			return(Math.log(i)/Math.log(2));
	}
	public int label(Set trainingSet) {
		int pPlus=0, pNeg=0;
		pPlus=getPPlus(attributeIndex,trainingSet);
		pNeg=getPNeg(attributeIndex,trainingSet);
		if(pPlus<pNeg)
			return 0;
		else 
			return 1;
	}	

}
