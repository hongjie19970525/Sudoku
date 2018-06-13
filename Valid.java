package com;

import java.util.Arrays;

class Valid{
	int [][]a;
	public Valid(int[][] a) {
		this.a=a;
	}
	public boolean valid() {
		int i,j,k,m,z,n;
		//对行进行判断
	   for(i=0;i<9;i++){
		   for(j=0,k=1,z=0;j<9;j++,k++){
			   for(m=0;m<9;m++)
				   if(a[i][m]==k) {
					   z++;break;
					   }
		    	}   
		   	if(z!=9) {
		   		n=i+1;
		   		System.out.println("第"+n+"行错误");
		   		return false;
		   		}    
		   }   
		//对列进行判断   
		   for(i=0;i<9;i++){
			   for(j=0,k=1,z=0;j<9;j++,k++){
				   for(m=0;m<9;m++)
					   if(a[m][i]==k) {
						   z++;break;
						   }
		   			}  
			   	if(z!=9) {
			   		n=i+1;
			   		System.out.println("第"+n+"列错误");
			   		return false;
			   		}
		        }
		//对小方块进行判断
		  for(k=0;k<=6;k+=3){
			  for(n=0;n<=6;n+=3){
				  for(m=1,z=0;m<=9;m++){
					  for(i=k;i<=(k+2);i++){
						  for(j=n;j<=(n+2);j++){
							  if(a[i][j]==m) {z++;break;}
						  }
						  if(z==m) break;
				  			}
					  }
		      if(z!=9) {
		    	  System.out.println("ERROR");
		    	  return false;
		    	  }     
		     }
		 }
		System.out.println("正确");
		return true;
		 }
	}
	
	

