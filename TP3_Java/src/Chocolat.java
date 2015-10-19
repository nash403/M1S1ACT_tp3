import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Chocolat {

	/**
	 * La largeur
	 */
	public int m;

	/**
	 * La longueur
	 */
	public int n;

	/**
	 * La position en x du chocolat empoisonné
	 */
	public int x;

	/**
	 * La position en y du chocolat empoisonné
	 */
	public int y;

	public static int getValue_naif(int m, int n, int x, int y) {
		List<Integer> pos = new LinkedList<Integer>();
		List<Integer> neg = new LinkedList<Integer>();

		if ((m == n) && (n == 1) && (x == y) && (x == 0)) {
			return 0;
		} else {
			for (int i = 1; i <= x; i++) {
				int res = getValue_naif(m - i, n, x - i, y);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}
			for (int i = x + 1; i < m; i++) {
				int res = getValue_naif(i, n, x, y);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}
			for (int i = 1; i <= y; i++) {
				int res = getValue_naif(m, n - i, x, y - i);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}
			for (int i = y + 1; i < n; i++) {
				int res = getValue_naif(m, i, x, y);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}

			if (neg.isEmpty()) {
				return -((maximum(pos)).intValue() + 1);
			} else {
				return -(maximum(neg)).intValue() + 1;
			}

		}
	}

	public static int getValue_dynammique(int m, int n, int i, int j) {
		int[][][][] values = constructArray(m, n);

		return values[m][n][i][j];
	}

	/*
	 * private static int[][][][] constructArray(int m, int n) { int[][][][]
	 * values = new int[m + 1][n + 1][m + 1][n + 1]; values[1][1][0][0] = 0;
	 * 
	 * for (int mp = 1; mp <= m; mp++) { for (int np = 1; np <= n; np++) { for
	 * (int i = 0; i < mp; i++) { for (int j = 0; j < np; j++) { if (!((mp == 1)
	 * && (np == 1) && (i == 0) && (j == 0))) { List<Integer> pos = new
	 * LinkedList<Integer>(); List<Integer> neg = new LinkedList<Integer>(); for
	 * (int k = 1; k <= i; k++) { int res = values[mp - k][np][i - k][j]; if
	 * (res > 0) { pos.add(Integer.valueOf(res)); } else {
	 * neg.add(Integer.valueOf(res)); } } for (int k = i + 1; k < mp; k++) { int
	 * res = values[k][np][i][j]; if (res > 0) { pos.add(Integer.valueOf(res));
	 * } else { neg.add(Integer.valueOf(res)); } } for (int k = 1; k <= j; k++)
	 * { int res = values[mp][np - k][i][j - k]; if (res > 0) {
	 * pos.add(Integer.valueOf(res)); } else { neg.add(Integer.valueOf(res)); }
	 * } for (int k = j + 1; k < np; k++) { int res = values[mp][k][i][j]; if
	 * (res > 0) { pos.add(Integer.valueOf(res)); } else {
	 * neg.add(Integer.valueOf(res)); } }
	 * 
	 * if (neg.isEmpty()) { values[mp][np][i][j] = -((maximum(pos)).intValue() +
	 * 1); } else { values[mp][np][i][j] = -(maximum(neg)).intValue() + 1; } } }
	 * } } } return values; }
	 */
	public static int[][][][] constructArray(int m, int n) {
		int[][][][] values = new int[m + 1][n + 1][][];
		values[1][1] = new int[1][1];
		values[1][1][0][0] = 0;
		// System.out.println("construct avec m="+m+", n="+n);
		for (int mp = 1; mp <= m; mp++) {
			for (int np = 1; np <= n; np++) {
				// System.out.println("boucle avec mp="+mp+", np="+np);
				values[mp][np] = new int[mp][np];

				for (int i = 0; i < mp; i++) {
					for (int j = 0; j < np; j++) {
						if (!((mp == 1) && (np == 1) && (i == 0) && (j == 0))) {

							List<Integer> pos = new LinkedList<Integer>();
							List<Integer> neg = new LinkedList<Integer>();
							// System.out.println("- Début (" + mp + "," + np +
							// "," + i + "," + j + ")");
							for (int k = 1; k <= i; k++) {
								int res = values[mp - k][np][i - k][j];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
								// System.out.println("1. on ajoute "+res);
							}
							for (int k = i + 1; k < mp; k++) {
								int res = values[k][np][i][j];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
								// System.out.println("2. on ajoute "+res);
							}
							for (int k = 1; k <= j; k++) {
								int res = values[mp][np - k][i][j - k];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
								// System.out.println("3. on ajoute "+res);
							}
							for (int k = j + 1; k < np; k++) {
								int res = values[mp][k][i][j];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
								// System.out.println("4. on ajoute "+res);
							}
							/*
							 * System.out.println("pos"); for (Integer p : pos)
							 * { System.out.print(p + " "); }
							 * System.out.println("\nneg"); for (Integer ne :
							 * neg) { System.out.print(ne + " "); }
							 * System.out.println();
							 */

							if (neg.isEmpty()) {
								values[mp][np][i][j] = -((maximum(pos)).intValue() + 1);
							} else {
								values[mp][np][i][j] = -(maximum(neg)).intValue() + 1;
							}
							// System.out.println("On trouve (" + mp + "," + np
							// + "," + i + "," + j + ") = " +
							// values[mp][np][i][j]);
						}
					}
				}
			}
		}
		return values;

	}

	public static List<Point> getPossiblePosition(int m, int n, int value) {
		List<Point> result = new LinkedList<Point>();
		int[][][][] values = constructArray(m, n);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (values[m][n][i][j] == value) {
					result.add(new Point(i, j));
				}
			}
		}

		return result;
	}

	public static Integer maximum(List<Integer> list) {
		Integer tmp = list.get(0);
		for (Integer i : list) {
			if (i.compareTo(tmp) > 0) {
				tmp = i;
			}
		}
		return tmp;
	}

	public static void main(String args[]) throws Exception {
		long deb,tot;
		tot = System.nanoTime();
		System.out.println("****************Algorithme naif****************");
		System.out.println("Valeur de (3,2,2,0) : " + getValue_naif(3, 2, 2, 0));
		System.out.println("Valeur de (3,1,2,0) : " + getValue_naif(3, 1, 2, 0));
		System.out.println("Valeur de (3,2,1,0) : " + getValue_naif(2, 2, 1, 0));
		System.out.println("Valeur de (3,1,1,0) : " + getValue_naif(2, 1, 1, 0));
		System.out.println("Valeur de (1,2,0,1) : " + getValue_naif(1, 2, 0, 1));
		System.out.println("Valeur de (1,1,0,0) : " + getValue_naif(1, 1, 0, 0));
		deb = System.nanoTime();
		System.out.println("Valeur de (10,7,7,3) : " + getValue_naif(10, 7, 7, 3));
		System.out
				.println("Le temps que (10, 7, 7, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		deb = System.nanoTime();
		System.out.println("Valeur de (10,7,5,3) : " + getValue_naif(10, 7, 5, 3));
		System.out
				.println("Le temps que (10, 7, 5, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");

		System.out.println("****************Algorithme dynamique****************");
		System.out.println("Valeur de (3,2,2,0) : " + getValue_dynammique(3, 2, 2, 0));
		System.out.println("Valeur de (3,1,2,0) : " + getValue_dynammique(3, 1, 2, 0));
		System.out.println("Valeur de (2,2,1,0) : " + getValue_dynammique(2, 2, 1, 0));
		System.out.println("Valeur de (2,1,1,0) : " + getValue_dynammique(2, 1, 1, 0));
		System.out.println("Valeur de (1,2,0,1) : " + getValue_dynammique(1, 2, 0, 1));
		System.out.println("Valeur de (1,1,0,0) : " + getValue_dynammique(1, 1, 0, 0));
		deb = System.nanoTime();
		System.out.println("Valeur de (10,7,7,3) : " + getValue_dynammique(10, 7, 7, 3));
		System.out
				.println("Le temps que (10, 7, 7, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		deb = System.nanoTime();
		System.out.println("Valeur de (10,7,5,3) : " + getValue_dynammique(10, 7, 5, 3));
		System.out
				.println("Le temps que (10, 7, 5, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		deb = System.nanoTime();
		System.out.println("Valeur de (100,100,50,50) : " + getValue_dynammique(100, 100, 50, 50));
		System.out.println(
				"Le temps que (100, 100, 50, 50) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		deb = System.nanoTime();
		System.out.println("Valeur de (100,100,48,52) : " + getValue_dynammique(100, 100, 48, 52));
		System.out.println(
				"Le temps que (100, 100, 48, 52) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		deb = System.nanoTime();
		List<Point> points = getPossiblePosition(127, 127, 127);
		for (Point result : points) {
			System.out.println("L'un des points possible qui a la valeur 127 est le suivant : (" + result.getX() + ", "
					+ result.getY() + ")");
		}
		System.out.println(
				"Le temps que getPossiblePosition a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		System.out.println(
				"Le temps que l'ensemble du programme a prit " + ((System.nanoTime() - tot) / 1000000000) + " secondes");
		System.out.println("Fin du programme");
	}

}
