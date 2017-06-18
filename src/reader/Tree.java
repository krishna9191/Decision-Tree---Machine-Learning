package reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Tree {

	
	
	int[] checkList={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	Queue<node> queue1 = new LinkedList<node>();
	list nodeBank=new list();
	 public int[] getCheckList() {
		return checkList;
	}
	 public void setCheckList(int[] checkList) {
		for(int i=0;i<checkList.length;i++)
		 this.checkList[i] = checkList[i];
		
	}



	public  list growTree(node root, Set trainingSet, boolean gainFlag)
	{	
			if(!(queue1.isEmpty()&&root.parent!=null))
		{
		root.visited=true;
		boolean checkListFlag=false,checkingFlag=false;
		int i=0,index=0;
		double[]gain=new double[7];
		 gain[2]=-1;
		double[]temp=new double[7];
		for(i=0;i<trainingSet.attribCount-1;i++)
			{   if(this.checkList[i]==0)
					{  checkListFlag=true;
						break;
					}
			}
		if(checkListFlag)
			{ for(i=0;i<trainingSet.attribCount-1;i++)
				{ if(this.checkList[i]==0)
				  {  temp= root.findGain(trainingSet,root.getAttributeIndex(),i,gainFlag);
					  		if(gain[2]<temp[2])
					  			{ for(int n=0;n<7;n++)
					  				gain[n]=temp[n];
					  				index=i;
					  				checkingFlag=true;
					  			}
				  }				
				}
			if(checkingFlag){
		  		this.checkList[index]=1;
				node lchild=new node();
				  node rchild=new node();
				rchild.setParent(root);
		  		lchild.setParent(root);
				if(gain[3]==0)
					{	int label1,label2;
	  					label1=root.getPNeg(root.getAttributeIndex(), trainingSet);
	  					label2=root.getPPlus(root.getAttributeIndex(), trainingSet);
	  					if(label2>=label1)
	  						rchild.label=1;
	  					else
	  						{
	  						rchild.label=0;
	  						}
					
					}
				if(gain[4]==0)
					{
						int label1,label2;
						label1=root.getPNeg(root.getAttributeIndex(), trainingSet);
						label2=root.getPPlus(root.getAttributeIndex(), trainingSet);
						if(label2>=label1)
							lchild.label=1;
						else
  							{
								lchild.label=0;
  							}
					}
		  		root.setAttribute(index);
				if(gain[3]!=0)
					{	if(gain[1]==0)
							{	int label1,label2;
								label1=root.getPNeg(root.getAttributeIndex(), trainingSet);
								label2=root.getPPlus(root.getAttributeIndex(), trainingSet);
								if(label2>=label1)
									rchild.label=1;
								else
								{
									rchild.label=0;
								}
							}
					}
				if(gain[4]!=0)
					{	if(gain[0]==0)
						{
							int label1,label2;
							label1=root.getPNeg(root.getAttributeIndex(), trainingSet);
							label2=root.getPPlus(root.getAttributeIndex(), trainingSet);
							if(label2>=label1)
								lchild.label=1;
							else
  								{
									lchild.label=0;
  								}
						}			
					}


				
			  	root.setLeft(lchild);
			  	lchild.setAttributeIndex(attributeIndexSetFalse(root.getAttributeIndex(),index));
			  	root.setRight(rchild);
			  	rchild.setAttributeIndex(attributeIndexSetTrue(root.getAttributeIndex(),index));
			  	queue1.add(lchild);
			  	queue1.add(rchild);
			  checkList[root.attribute]=1;
			 System.out.print(root.attribute+"  "+root.label+"   "); root.print(root.getAttributeIndex());
			nodeBank.add(root); 
			 root=queue1.remove();

			  growTree(root, trainingSet, gainFlag);
			}
		
	}
		else
			{
			  root.setLabel(root.label(trainingSet));
				 System.out.print(root.attribute+"  "+root.label+"   "); root.print(root.getAttributeIndex());
			  nodeBank.add(root);
			  root=queue1.remove();
			  growTree(root,trainingSet,gainFlag);
			   			  
			}
		}
	
		return nodeBank; 
		
	}


	private int[] attributeIndexSetTrue(int[] attributeIndex, int index) {
		int[] attributeIndex2=new int[attributeIndex.length];
		for(int i=0;i<attributeIndex.length;i++)
			attributeIndex2[i]=attributeIndex[i];
		attributeIndex2[index]=1;
		return attributeIndex2;
	}

	private int[] attributeIndexSetFalse(int[] attributeIndex, int index) {
		int[] attributeIndex2=new int[attributeIndex.length];
		for(int i=0;i<attributeIndex.length;i++)
			attributeIndex2[i]=attributeIndex[i];
		attributeIndex2[index]=0;
		return attributeIndex2;
	}


	
	public void printTree(int i,int j, String[] attributes) {
		
		node root=null;
		

			root=nodeBank.get(j);
			if(root!=null)
			{
		 if(i>0){
		for(int n=0;n<i;j++)
			System.out.print("|    ");
		
		if(root.attribute!=-1)
			{	System.out.print(attributes[root.attribute]+"  = "+  root.getAttributeIndex()[root.attribute]+" : ");
				if(root.label==-1)
					System.out.print("  ");
				else System.out.print(root.label);
			}
		else
		{
			System.out.print(attributes[root.attribute]+"  = "+  root.getAttributeIndex()[root.attribute]+" : "+root.label);
		}
		System.out.println("");
		
	}
			
			printTree(i+1,2*j+1,attributes);
	
			printTree(i+1,(2*j+1),attributes);
			}
	}
	
	public double getaccuracy(Set testSet) {
		
		double accuracy=0;
		int i=0,j=0,count=0;
		for(i=0;i<testSet.example;i++)
			for(j=0;j<testSet.attribCount;j++)
				;
		
		
		
		
		return accuracy;
	}
	public void remove(int p1) {
		this.remove(p1);
	
	}

	
	
	
	
	
	
	
	
	

		
	}


	


