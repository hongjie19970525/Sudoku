package com;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JLabel;

public class MyFrame extends JFrame {
	public MyFrame() {
		this.setSize(500,600);
		this.setTitle("SudoKu");
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class MyPanel extends JPanel{
		public MyPanel() {
			this.setSize(800, 800);
			this.setBounds(50,50,800,800);
			this.setBackground(Color.BLACK);			
			
		}
	}
	static class MyLabel extends JLabel{
		public MyLabel() {
			this.setSize(44,44);
			this.setForeground(Color.CYAN);
			//this.setText(string);
			this.setOpaque(true);
			this.setBackground(Color.GREEN);
			this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame frame=new MyFrame();
		MyPanel mypanel=new MyPanel();
		JPanel panelCont=new JPanel();
		panelCont.setBounds(50,530,400,60);
		panelCont.setBackground(Color.GRAY);
		JButton Reset=new JButton("Reset");
		JButton Start=new JButton("Start");
		JButton Check=new JButton("Check");
		Reset.setBounds(10, 10, 100, 40);
		Start.setBounds(150, 10, 100, 40);
		Check.setBounds(290, 10 , 100, 40);
		panelCont.add(Reset);
		panelCont.add(Start);
		panelCont.add(Check);
		mypanel.setBounds(50,20,400,500);
		mypanel.setLayout(new GridLayout(9, 9));
		for(int i=0;i<81;i++)
		{
			mypanel.add(new MyLabel());
			
		}
		frame.getContentPane().add(mypanel);
		frame.getContentPane().add(panelCont);
	}
}
