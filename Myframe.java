package com;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Myframe {

	private JFrame frame;
	
	static class MyText1 extends JTextField {
		public MyText1() {
			this.setSize(44, 44);
			this.setForeground(Color.CYAN);
			this.setOpaque(true);
			this.setBackground(Color.GREEN);
			this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
	}
	static class MyPanel extends JPanel{
		public MyPanel() {
			this.setSize(800, 800);
			this.setBounds(50,50,800,800);
			this.setBackground(Color.BLACK);			
			
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Myframe window = new Myframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Myframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(500,600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		MyText[][] text =new MyText [9][9];
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9 ;j++) {
				text[i][j]=new MyText(44,44,"");				
				mypanel.add(text[i][j]);
			}
			
		}
		frame.getContentPane().add(mypanel);
		frame.getContentPane().add(panelCont);
		
		
		Start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DropNum sudo=new DropNum();
				Sudoku ku=new Sudoku();
				int[][] a=new int[9][9];
				a=ku.madeShuDu();
				int[][] temp=new int[9][9];
				temp=sudo.DropANum(a);
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9 ;j++) {						
						text[i][j].setText(String.valueOf(temp[i][j]));
						if(temp[i][j]==0) {
							text[i][j].setText("");
					}
					}
					
				}
				
			}
		});
		
		
		Reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						text[i][j].setText("");
					}
				}
			}
		});
		
		Check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int [][] board=new int[9][9];
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {
						if(text[i][j].getText().equals("")) {
							text[i][j].setText("0");
						}
						
					}
				}
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++) {						
						board[i][j]=Integer.parseInt(text[i][j].getText());
					}
					
				}
				Valid valid=new Valid(board);
				
				if(!valid.valid()) {
					Reset.setBackground(Color.RED);
				}
			}
		});
	}

}
