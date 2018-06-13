package com;

import java.util.Random;


public class Sudoku {
	/**
	 * 数独矩阵数组
	 */
	int[][] shuDu = new int[9][9];
	/**
	 * 已经被使用的数字数组
	 */
	int[] wasUsed = new int[9];
	/**
	 * 一个点无法使用的数字
	 */
	int[][][] canNotUse = new int[9][9][9];
	/**
	 * 随机产生数字对象
	 */
	Random random = new Random();
	/**
	 * 執行次數
	 */
	int count = 0;
	int tag = 0;

	/**
	 * 找出已经被使用的数字
	 */

	public void numWasUsed(int i, int j) {
		// System.out.println("---------------numWasUsed----------");
		int x = 1, y = 0, z = 0;
		for (y = 0; y < j; y++) {// 检查横行已有数字
			for (x = 1; x < 10; x++) {
				if (shuDu[i][y] == x) {
					wasUsed[x - 1] = x;
					break;
				}
			}
		}
		for (y = 0; y < i; y++) {// 检查竖行已有数字
			for (x = 1; x < 10; x++) {
				if (shuDu[y][j] == x) {
					wasUsed[x - 1] = x;
					break;
				}
			}
		}
		int[] s = cancuSquare(i, j);// 获取矩形
		for (y = s[0]; y < s[0] + 3; y++) {// 检查矩已有数字
			for (z = s[1]; z < s[1] + 3; z++) {
				if (y > i - 1 && z > j - 1) {// 超出範圍
					y = 10;
					z = 10;
					break;
				}
				for (x = 1; x < 10; x++) {
					if (shuDu[y][z] == x) {
						wasUsed[x - 1] = x;
						break;
					}
				}
			}
		}
	}

	/**
	 * 计算矩形宫格初始点位，返回int[2],横坐标，竖坐标
	 */
	public int[] cancuSquare(int i, int j) {
		if (i < 3) {
			i = 0;
		} else if (i < 6) {
			i = 3;
		} else if (i < 9) {
			i = 6;
		}
		if (j < 3)
			j = 0;
		else if (j < 6) {
			j = 3;
		} else if (j < 9) {
			j = 6;
		}
		int[] a = { i, j };
		return a;
	}

	/**
	 * 检测数字是否会造成下一点无数字可填
	 */
	public int checkNumIsRight(int[][][] a, int[] b, int v, int i, int j) {
		// System.out.println("---------------checkNumIsRight----------");
		int x = 0;
		int[] add = new int[9];
		for (int k = 0; k < 9; k++) {
			if (a[i][j][k] != 0)
				add[k] = a[i][j][k];
			if (b[k] != 0)
				add[k] = b[k];
		}
		for (x = 0; x < 9; x++) {
			if (add[x] == 0)
				break;
			else if (x == 8)
				return 1;
		}
		for (x = 0; x < 9; x++) {
			if (v == a[i][j][x]) {
				return 0;
			}
		}
		for (x = 0; x < 9; x++) {
			if (v == b[x]) {
				return 0;
			}
		}
		return 2;
	}

	/**
	 * 主流程
	 */
	public int[][] madeShuDu() {
		int i = 0, j = 0;
		while (i < 9) {
			j = 0;
			while (j < 9) {
				// System.out.println("i=:" + i + ";j=" + j);
				count++;
				int values = random.nextInt(9) + 1;
				/**
				 * 查找該行該列該矩陣已出現的數字
				 */
				numWasUsed(i, j);
				/**
				 * 判斷values是否可用
				 */
				/*
				 * for (int f = 0; f < 9; f++) {// 初始化兩個shuzu
				 * System.out.print(canNotUse[i][j][f]+" "); }
				 * System.out.println(); for (int f = 0; f < 9; f++) {//
				 * 初始化兩個shuzu System.out.print(wasUsed[f]+" "); }
				 * System.out.println();
				 */
				int cha = checkNumIsRight(canNotUse, wasUsed, values, i, j);
				if (cha != 2) {// 不可用
					if (cha == 0)// 有其他可用數字
					{
						// System.out.println("assass");
						continue;
					} // 重新隨機
					else if (cha == 1) {// 無其他可用數字

						for (int f = 0; f < 9; f++) {// 初始化兩個shuzu
							if (j == 8) {
								canNotUse[i + 1][0][f] = 0;
							} else {
								canNotUse[i][j + 1][f] = 0;
							}

							wasUsed[f] = 0;
						}
						if (j == 0 ) {// 退回到上一格并將上一格現有數字標記不可用
							j = 8;
							i = i - 1;
						} else{
							// i = i - 1;
							j = j - 1;

						}
						try {
							canNotUse[i][j][shuDu[i][j] - 1] = shuDu[i][j];
						} catch (Exception e) {
							System.out.println(i+" "+j);
						}
						
						continue;
					}
				}
				for (int f = 0; f < 9; f++) {
					// canNotUse[i][j][f] = 0;
					wasUsed[f] = 0;
				}
				shuDu[i][j] = values;
				j++;

			}
			i++;
		}
		return shuDu;
	}

	public void outShudu(int[][] a) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(a[i][j] + " ");
				if ((j + 1) % 3 == 0) {
					System.out.print("|");
				}
			}

			if ((i + 1) % 3 == 0) {
				System.out.println();
				System.out.println("________________________");
			} else {
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		
		int i = 0;
		int allcount=0;
		long startimr=System.currentTimeMillis();
		while (i++ < 10000) {
			Sudoku aShudu = new Sudoku();
			aShudu.madeShuDu();
			aShudu.outShudu(aShudu.shuDu);
			allcount+=aShudu.count;
			System.out.println("-----------------woshi di "+ i+" ge-------------------");
		}
		long endtimr=System.currentTimeMillis();
		System.out.println("时间："+((endtimr-startimr)/1000.00));
	System.out.println("平均产生每个数独循环次数："+allcount/(i+1));
	}

}