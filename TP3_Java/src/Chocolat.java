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

	public static int getValue(int m, int n, int x, int y) {
		List<Integer> pos = new LinkedList<Integer>();
		List<Integer> neg = new LinkedList<Integer>();

		if ((m == n) && (n == 1) && (x == y) && (x == 0)) {
			return 0;
		} else {
			for (int i = 1; i <= x; i++) {
				int res = getValue(m - i, n, x - i, y);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}
			for (int i = x + 1; i < m; i++) {
				int res = getValue(i, n, x, y);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}
			for (int i = 1; i <= y; i++) {
				int res = getValue(m, n - i, x, y - i);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}
			for (int i = y + 1; i < n; i++) {
				int res = getValue(m, i, x, y);
				if (res > 0) {
					pos.add(Integer.valueOf(res));
				} else {
					neg.add(Integer.valueOf(res));
				}
			}

			if (neg.isEmpty()) {
				return -((maximum(pos)).intValue() + 1);
			} else {
				return -(maximum(neg)).intValue() + 1 ;
			}

		}
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
		System.out.println(getValue(3, 2, 2, 0));
		System.out.println(getValue(3, 1, 2, 0));
		System.out.println(getValue(2, 2, 1, 0));
		System.out.println(getValue(2, 1, 1, 0));
		System.out.println(getValue(1, 2, 0, 1));
		System.out.println(getValue(1, 1, 0, 0));
		long deb= System.nanoTime();
		System.out.println(getValue(10, 7, 7, 3));
		System.out.println("Le temps que (10, 7, 7, 3) a prit " + (System.nanoTime() - deb));
		deb = System.nanoTime();
		System.out.println(getValue(10, 7, 5, 3));
		System.out.println("Le temps que (10, 7, 5, 3) a prit " + (System.nanoTime() - deb));
	}

}
