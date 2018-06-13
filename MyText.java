package com;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class MyText extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH=0;
	private int HEIGHT=0;
	private String str="";
	
	public MyText(int width,int height,String str) {
		this.WIDTH=width;
		this.HEIGHT=height;
		this.str=str;
		this.setText(str);
		this.setSize(WIDTH,HEIGHT);
		this.setHorizontalAlignment(JTextField.CENTER);
		//为啥颜色不能显示出来呢？？？	
		this.setForeground(Color.RED);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		Font f=new Font("Ariel",Font.BOLD,20);
		this.setFont(f);
		this.setOpaque(true);
		this.setEnabled(true);
		
		
		
	}
	
}
