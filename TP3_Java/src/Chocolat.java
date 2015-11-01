import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Chocolat {

	public static int getValue_naif(int m, int n, int x, int y) {
		List<Integer> pos = new LinkedList<Integer>();
		List<Integer> neg = new LinkedList<Integer>();

		if ((m == n) && (n == 1) && (x == y) && (x == 0)) {
			return 0;
		}
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

	public static int getValue_dynamique(int m, int n, int i, int j) {
		int[][][][] tablet = initTablet(m, n);

		return m >= n ? tablet[m][n][i][j] : tablet[n][m][j][i];
	}

	public static int[][][][] initTablet(int l, int p) {
		int m, n;
		if (l >= p) {
			m = l;
			n = p;
		} else {
			m = p;
			n = l;
		}

		int[][][][] tablet = new int[m + 1][n + 1][][];
		tablet[1][1] = new int[1][1];
		tablet[1][1][0][0] = 0;
		for (int mp = 1; mp <= m; mp++) {
			for (int np = 1; np <= mp && np <= n; np++) {
				tablet[mp][np] = new int[mp][np];
				if (mp <= n && np <= mp)
					tablet[np][mp] = new int[np][mp];

				for (int i = 0, sti = ((mp / 2) + 1); i < sti; i++) {
					for (int j = 0, stj = ((np / 2) + 1); j < stj; j++) {
						if (!((mp == 1) && (np == 1) && (i == 0) && (j == 0))) {

							List<Integer> pos = new LinkedList<Integer>();
							List<Integer> neg = new LinkedList<Integer>();
							for (int k = 1; k <= i; k++) {
								int res = tablet[mp - k][np][i - k][j];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
							}
							for (int k = i + 1; k < mp; k++) {
								int res = tablet[k][np][i][j];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
							}
							for (int k = 1; k <= j; k++) {
								int res = tablet[mp][np - k][i][j - k];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
							}
							for (int k = j + 1; k < np; k++) {
								int res = tablet[mp][k][i][j];
								if (res > 0) {
									pos.add(Integer.valueOf(res));
								} else {
									neg.add(Integer.valueOf(res));
								}
							}

							if (neg.isEmpty()) {
								tablet[mp][np][i][j] = -((maximum(pos)).intValue() + 1);
							} else {
								tablet[mp][np][i][j] = -(maximum(neg)).intValue() + 1;
							}
							if (mp <= n && np <= mp)
								tablet[np][mp][j][i] = tablet[mp][np][i][j];
							/*
							 * Les 3 autres configurations équivalentes à
							 * (mp,np,i,j)
							 */
							tablet[mp][np][i][np - j - 1] = tablet[mp][np][i][j];
							tablet[mp][np][mp - i - 1][j] = tablet[mp][np][i][j];
							tablet[mp][np][mp - i - 1][np - j - 1] = tablet[mp][np][i][j];

							if (mp <= n && np <= mp) {
								/*
								 * Les 3 autres configurations équivalentes à
								 * (np,mp,j,i)
								 */
								tablet[np][mp][np - j - 1][i] = tablet[mp][np][i][j];
								tablet[np][mp][j][mp - i - 1] = tablet[mp][np][i][j];
								tablet[np][mp][np - j - 1][mp - i - 1] = tablet[mp][np][i][j];
							}
						}
					}
				}
			}
		}
		return tablet;
	}

	public static List<Point> getPossiblePosition(int m, int n, int value) {
		List<Point> result = new LinkedList<Point>();
		int[][][][] tablet = initTablet(m, n);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (tablet[m][n][i][j] == value) {
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

	public static Configuration best(List<Configuration> list) {
		Configuration tmp = list.get(0);
		for (Configuration i : list) {
			if (i.value > tmp.value) {
				tmp = i;
			}
		}
		return tmp;
	}

	public static Configuration getBestSucc(List<Configuration> possibilities) {
		List<Configuration> pos = new LinkedList<Configuration>();
		List<Configuration> neg = new LinkedList<Configuration>();
		for (Configuration c : possibilities) {
			if (c.value <= 0)
				neg.add(c);
			else
				pos.add(c);
		}
		if (neg.isEmpty()) {
			return best(pos);
		} else {
			return best(neg);
		}
	}

	public static void game(int m, int n, int i, int j) {
		Scanner keyboard = new Scanner(System.in);
		int[][][][] tablet = initTablet(m, n);
		boolean cpuTurnToPlay = true;
		int input;
		Chocolat choco = new Chocolat();
		Configuration actual = choco.new Configuration(m, n, i, j, tablet[m][n][i][j]);

		while (actual.value != 0) {
			List<Configuration> possibilities = new ArrayList<Configuration>();

			System.out.println("================================================");

			if (cpuTurnToPlay)
				System.out.println("***CPU turn to play***");
			else
				System.out.println("***Your turn to play***");

			System.out.println("Actual configuration of the tablet: " + actual);
			System.out.println("All possibilities :");

			int cpt = 1;

			for (int k = 1; k <= actual.i; k++) {
				possibilities.add(choco.new Configuration(actual.m - k, actual.n, actual.i - k, actual.j,
						tablet[actual.m - k][actual.n][actual.i - k][actual.j]));
			}
			for (int k = actual.i + 1; k < actual.m; k++) {
				possibilities.add(choco.new Configuration(k, actual.n, actual.i, actual.j,
						tablet[k][actual.n][actual.i][actual.j]));
			}
			for (int k = 1; k <= actual.j; k++) {
				possibilities.add(choco.new Configuration(actual.m, actual.n - k, actual.i, actual.j - k,
						tablet[actual.m][actual.n - k][actual.i][actual.j - k]));
			}
			for (int k = actual.j + 1; k < actual.n; k++) {
				possibilities.add(choco.new Configuration(actual.m, k, actual.i, actual.j,
						tablet[actual.m][k][actual.i][actual.j]));
			}
			for (Configuration conf : possibilities)
				System.out.println((cpt++) + ". " + conf);
			if (!cpuTurnToPlay) {
				System.out.println("(enter the number of the configuration you want to play)");
				while (true) {

					System.out.print("Make your play >");
					input = keyboard.nextInt() - 1;
					if (input >= 0 && input < possibilities.size())
						break;
					System.out.println(":( Wrong answer, try again.");
				}

				System.out.print("You choosed ");
				actual = possibilities.get(input);
				System.out.println(actual);
			} else {
				System.out.print("CPU choosed ");
				actual = getBestSucc(possibilities);
				System.out.println(actual);
			}
			cpuTurnToPlay = !cpuTurnToPlay;
		}

		System.out.println("================================================");
		if (cpuTurnToPlay)
			System.out.println("You win !!");
		else
			System.out.println("CPU Wins !");
		keyboard.close();
	}

	public static void main(String args[]) throws Exception {
		// Décommenter pour tester l'algo

		// long deb, tot;
		// tot = System.nanoTime();
		// System.out.println("****************Algorithme naif****************");
		// System.out.println("Valeur de (3,2,2,0) : " + getValue_naif(3, 2, 2, 0));
		// System.out.println("Valeur de (3,1,2,0) : " + getValue_naif(3, 1, 2, 0));
		// System.out.println("Valeur de (3,2,1,0) : " + getValue_naif(2, 2, 1, 0));
		// System.out.println("Valeur de (3,1,1,0) : " + getValue_naif(2, 1, 1, 0));
		// System.out.println("Valeur de (1,2,0,1) : " + getValue_naif(1, 2, 0, 1));
		// System.out.println("Valeur de (1,1,0,0) : " + getValue_naif(1, 1, 0, 0));
		// deb = System.nanoTime();
		// System.out.println("Valeur de (10,7,7,3) : " + getValue_naif(10, 7, 7, 3));
		// System.out
		// 		.println("Le temps que (10, 7, 7, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		// deb = System.nanoTime();
		// System.out.println("Valeur de (10,7,5,3) : " + getValue_naif(10, 7, 5, 3));
		// System.out
		// 		.println("Le temps que (10, 7, 5, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		//
		// System.out.println("****************Algorithme dynamique****************");
		// deb = System.nanoTime();
		// System.out.println("Valeur de (10,7,7,3) : " + getValue_dynamique(10, 7, 7, 3));
		// System.out
		// 		.println("Le temps que (10, 7, 7, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		// deb = System.nanoTime();
		 System.out.println("Valeur de (10,7,5,3) : " + getValue_dynamique(10, 7, 5, 3));
		// System.out
		// 		.println("Le temps que (10, 7, 5, 3) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		// deb = System.nanoTime();
		// System.out.println("Valeur de (100,100,50,50) : " + getValue_dynamique(100, 100, 50, 50));
		// System.out.println(
		// 		"Le temps que (100, 100, 50, 50) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		// deb = System.nanoTime();
		 System.out.println("Valeur de (100,100,48,52) : " + getValue_dynamique(100, 100, 48, 52));
		// System.out.println(
		// 		"Le temps que (100, 100, 48, 52) a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		// deb = System.nanoTime();
		 List<Point> points = getPossiblePosition(127, 127, 127);
		 for (Point result : points) {
		 	System.out.println("L'un des points possible qui a la valeur 127 est le suivant : (" + result.getX() + ", "
		 			+ result.getY() + ")");
		 }
		// System.out.println(
		// 		"Le temps que getPossiblePosition a prit " + ((System.nanoTime() - deb) / 1000000000) + " secondes");
		// System.out.println("Fin du test");
		// System.out.println("Le temps que l'ensemble du test a prit " + ((System.nanoTime() - tot) / 1000000000)
		// 		+ " secondes");

		// Décommenter pour tester le jeu

		// int m = Integer.parseInt(args[0]);
		// int n = Integer.parseInt(args[1]);
		// int i = Integer.parseInt(args[2]);
		// int j = Integer.parseInt(args[3]);
		// game(m, n, i, j);

	}

	public class Configuration {
		public int m, n, i, j, value;

		public Configuration(int m, int n, int i, int j, int value) {
			this.m = m;
			this.n = n;
			this.i = i;
			this.j = j;
			this.value = value;
		}

		public String toString() {
			return "(" + m + "," + n + "," + i + "," + j + ")";
		}
	}

}
