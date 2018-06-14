package com;

import java.util.Random;

public class DropNum extends Sudoku {
	//int[][] questionShudu = new int[9][9];
	int counts = 0;//每产生一个题目循环次数
	int emptyPoint=0;//题目中所挖的孔
	int[][] pointWasDrop = new int[9][9];//标记数组，用来记录数独中某个点是否被被挖到过，挖到记为1

	public int[][] DropANum(int[][] a) {
		int[] level = { 32, 45, 50, 53, 58 };//等级，未用到

		Random random = new Random();
		while (true) {
			counts++;
			int i = random.nextInt(9);
			int j = random.nextInt(9);//随机产生数独中一个点
			pointWasDrop[i][j] = 1;//标记改点已被挖
			if (a[i][j] == 0) {//如果改点值已经是0，重新选点

				continue;
			}
			conllectNum(i, j,a);//收集除改点以外，该点所在横，竖，9宫格其他数，收集结果存到wasUsed[]zhong
			if (!checkNumIsOnlyOne(wasUsed)) {//检查wasUsed中0是否唯一不是说名该点挖掉会有多个解
				for (int asd = 0; asd < 9; asd++) {
					wasUsed[asd] = 0;
				}
				if (!wasDo()) {//检查是否所有点都被挖到,是结束
					break;
				}
				
				continue;
			}
			a[i][j] = 0;//挖去该点的数
			emptyPoint++;
		}
		
		return a;
	}

	public boolean checkNumIsOnlyOne(int[] b) {
		int x = 0, i = 0;
		for (x = 0; x < 9; x++) {
			if (b[x] == 0) {
				i++;
			}
		}
		if (i == 1)
			return true;
		else {
			return false;
		}
	}
	public void conllectNum(int i, int j,int[][] a) {
		// System.out.println("---------------numWasUsed----------");
		int x = 1, y = 0, z = 0;
		for (y = 0; y < 9; y++) {// 检查横行已有数字
			for (x = 1; x < 10; x++) {
				if (a[i][y] == x) {
					wasUsed[x - 1] = x;
					break;
				}
			}
		}
		for (y = 0; y < 9; y++) {// 检查竖行已有数字
			for (x = 1; x < 10; x++) {
				if (a[y][j] == x) {
					wasUsed[x - 1] = x;
					break;
				}
			}
		}
		int[] s = cancuSquare(i, j);// 获取矩形
		for (y = s[0]; y < s[0] + 3; y++) {// 检查矩已有数字
			for (z = s[1]; z < s[1] + 3; z++) {
				/*if (y > i - 1 && z > j - 1) {// 超出範圍
					y = 10;
					z = 10;
					break;
				}*/
				for (x = 1; x < 10; x++) {
					if (a[y][z] == x) {
						wasUsed[x - 1] = x;
						break;
					}
				}
			}
		}
		wasUsed[a[i][j]-1]=0;
	}
	public boolean wasDo() {
		int x = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (pointWasDrop[i][j] != 1) {
					i = 10;
					break;
				}
				x++;
			}
		}
		if (x == 81)
			return false;
		else {
			return true;
		}

	}

	public static void main(String[] args) {
		
		int m = 0,ep=0,maxep=0,minep=0;
		while (m++ < 100) {
			DropNum aDropNum = new DropNum();
			aDropNum.madeShuDu();
			//aDropNum.questionShudu = aDropNum.shuDu;
			//System.out.println("as");
			//System.out.println("daan");
		//	aDropNum.outShudu(aDropNum.shuDu);
			//aDropNum.DropANum();
			aDropNum.outShudu(aDropNum.shuDu);
			System.out.println(aDropNum.counts);
			ep+=aDropNum.emptyPoint;
			if(m==1){
				minep=aDropNum.emptyPoint;
			}
			if(aDropNum.emptyPoint>maxep)
				maxep=aDropNum.emptyPoint;
			else if(aDropNum.emptyPoint<minep) {
				minep=aDropNum.emptyPoint;
			}
			System.out.println("+++++++++++++"+m+"+++++++++++++++++++++");
		}
		
		System.out.println("平均挖孔数="+ep/m+";最大挖孔数="+maxep+";最小挖孔数="+minep);
	}
}