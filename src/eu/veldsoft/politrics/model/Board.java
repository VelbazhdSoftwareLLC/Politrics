package eu.veldsoft.politrics.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Board {
	public static final int PIECES = 17;

	public static final int SIZE = 9;

	/**
	 * Initial container for all figures in the object model.
	 */
	private final Figure pile[][] = new Figure[2][PIECES];

	/**
	 * Two dimensional array with all figures on the board.
	 */
	private final Figure figures[][] = new Figure[SIZE][SIZE];

	/**
	 * Two dimensional array with fixed properties of the the board cells.
	 */
	private final Cell cells[][] = new Cell[SIZE][SIZE];

	/**
	 * Two dimensional array with civil servants aura.
	 */
	private final boolean aura[][] = new boolean[SIZE][SIZE];

	/**
	 * Container with figures placed in the line-ups.
	 */
	private final Map<Enemies, Vector<Figure>> lineups = new HashMap<Enemies, Vector<Figure>>();

	/**
	 * Board state as finite autmate.
	 */
	private State state = State.PLACING;

	/**
	 * Turn counter.
	 */
	private int turn = 0;

	/* Initialize object fields. */{
		pile[Enemies.DARK.index()][0] = new President(Enemies.DARK);
		pile[Enemies.DARK.index()][1] = new Voter(Enemies.DARK);
		pile[Enemies.DARK.index()][2] = new Voter(Enemies.DARK);
		pile[Enemies.DARK.index()][3] = new Voter(Enemies.DARK);
		pile[Enemies.DARK.index()][4] = new Voter(Enemies.DARK);
		pile[Enemies.DARK.index()][5] = new Minister(Enemies.DARK);
		pile[Enemies.DARK.index()][6] = new Minister(Enemies.DARK);
		pile[Enemies.DARK.index()][7] = new Minister(Enemies.DARK);
		pile[Enemies.DARK.index()][8] = new Minister(Enemies.DARK);
		pile[Enemies.DARK.index()][9] = new Delegate(Enemies.DARK);
		pile[Enemies.DARK.index()][10] = new Delegate(Enemies.DARK);
		pile[Enemies.DARK.index()][11] = new Delegate(Enemies.DARK);
		pile[Enemies.DARK.index()][12] = new Delegate(Enemies.DARK);
		pile[Enemies.DARK.index()][13] = new Servant(Enemies.DARK);
		pile[Enemies.DARK.index()][14] = new Servant(Enemies.DARK);
		pile[Enemies.DARK.index()][15] = new Servant(Enemies.DARK);
		pile[Enemies.DARK.index()][16] = new Servant(Enemies.DARK);

		pile[Enemies.LIGHT.index()][0] = new President(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][1] = new Voter(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][2] = new Voter(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][3] = new Voter(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][4] = new Voter(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][5] = new Minister(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][6] = new Minister(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][7] = new Minister(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][8] = new Minister(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][9] = new Delegate(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][10] = new Delegate(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][11] = new Delegate(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][12] = new Delegate(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][13] = new Servant(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][14] = new Servant(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][15] = new Servant(Enemies.LIGHT);
		pile[Enemies.LIGHT.index()][16] = new Servant(Enemies.LIGHT);

		lineups.put(Enemies.DARK, new Vector<Figure>());
		for (Figure figure : pile[Enemies.DARK.index()]) {
			lineups.get(Enemies.DARK).add(figure);
		}
		lineups.put(Enemies.LIGHT, new Vector<Figure>());
		for (Figure figure : pile[Enemies.LIGHT.index()]) {
			lineups.get(Enemies.LIGHT).add(figure);
		}

		cells[0][0] = new Cell(0xcc0000, 0, 11);
		cells[0][1] = new Cell(0xff99ff, 0, 12);
		cells[0][2] = new Cell(0xcc0000, 0, 13);
		cells[0][3] = new Cell(0xff99ff, 0, 14);
		cells[0][4] = new Cell(0xcc0000, 0, 15);
		cells[0][5] = new Cell(0xff99ff, 0, 16);
		cells[0][6] = new Cell(0xcc0000, 0, 17);
		cells[0][7] = new Cell(0xff99ff, 0, 18);
		cells[0][8] = new Cell(0xcc0000, 0, 19);

		cells[1][0] = new Cell(0xff99ff, 0, 21);
		cells[1][1] = new Cell(0x6699ff, 1, 22);
		cells[1][2] = new Cell(0xffffff, 0, 23);
		cells[1][3] = new Cell(0xffffff, 0, 24);
		cells[1][4] = new Cell(0x000099, 2, 25);
		cells[1][5] = new Cell(0xffffff, 0, 26);
		cells[1][6] = new Cell(0xffffff, 0, 27);
		cells[1][7] = new Cell(0x6699ff, 1, 28);
		cells[1][8] = new Cell(0xff99ff, 0, 29);

		cells[2][0] = new Cell(0xcc0000, 0, 31);
		cells[2][1] = new Cell(0xffffff, 0, 32);
		cells[2][2] = new Cell(0x6699ff, 3, 33);
		cells[2][3] = new Cell(0xffffff, 0, 34);
		cells[2][4] = new Cell(0x000099, 4, 35);
		cells[2][5] = new Cell(0xffffff, 0, 36);
		cells[2][6] = new Cell(0x6699ff, 3, 37);
		cells[2][7] = new Cell(0xffffff, 0, 38);
		cells[2][8] = new Cell(0xcc0000, 0, 39);

		cells[3][0] = new Cell(0xff99ff, 0, 41);
		cells[3][1] = new Cell(0xffffff, 0, 42);
		cells[3][2] = new Cell(0xffffff, 0, 43);
		cells[3][3] = new Cell(0x6699ff, 5, 44);
		cells[3][4] = new Cell(0x000099, 6, 45);
		cells[3][5] = new Cell(0x6699ff, 5, 46);
		cells[3][6] = new Cell(0xffffff, 0, 47);
		cells[3][7] = new Cell(0xffffff, 0, 48);
		cells[3][8] = new Cell(0xff99ff, 0, 49);

		cells[4][0] = new Cell(0xcc0000, 0, 51);
		cells[4][1] = new Cell(0x000099, 2, 52);
		cells[4][2] = new Cell(0x000099, 4, 53);
		cells[4][3] = new Cell(0x000099, 6, 54);
		cells[4][4] = new Cell(0x000000, 0, 55);
		cells[4][5] = new Cell(0x000099, 6, 56);
		cells[4][6] = new Cell(0x000099, 4, 57);
		cells[4][7] = new Cell(0x000099, 2, 58);
		cells[4][8] = new Cell(0xcc0000, 0, 59);

		cells[5][0] = new Cell(0xff99ff, 0, 61);
		cells[5][1] = new Cell(0xffffff, 0, 62);
		cells[5][2] = new Cell(0xffffff, 0, 63);
		cells[5][3] = new Cell(0x6699ff, 5, 64);
		cells[5][4] = new Cell(0x000099, 6, 65);
		cells[5][5] = new Cell(0x6699ff, 5, 66);
		cells[5][6] = new Cell(0xffffff, 0, 67);
		cells[5][7] = new Cell(0xffffff, 0, 68);
		cells[5][8] = new Cell(0xff99ff, 0, 69);

		cells[6][0] = new Cell(0xcc0000, 0, 71);
		cells[6][1] = new Cell(0xffffff, 0, 72);
		cells[6][2] = new Cell(0x6699ff, 3, 73);
		cells[6][3] = new Cell(0xffffff, 0, 74);
		cells[6][4] = new Cell(0x000099, 4, 75);
		cells[6][5] = new Cell(0xffffff, 0, 76);
		cells[6][6] = new Cell(0x6699ff, 3, 77);
		cells[6][7] = new Cell(0xffffff, 0, 78);
		cells[6][8] = new Cell(0xcc0000, 0, 79);

		cells[7][0] = new Cell(0xff99ff, 0, 81);
		cells[7][1] = new Cell(0x6699ff, 1, 82);
		cells[7][2] = new Cell(0xffffff, 0, 83);
		cells[7][3] = new Cell(0xffffff, 0, 84);
		cells[7][4] = new Cell(0x000099, 2, 85);
		cells[7][5] = new Cell(0xffffff, 0, 86);
		cells[7][6] = new Cell(0xffffff, 0, 87);
		cells[7][7] = new Cell(0x6699ff, 1, 88);
		cells[7][8] = new Cell(0xff99ff, 0, 89);

		cells[8][0] = new Cell(0xcc0000, 0, 91);
		cells[8][1] = new Cell(0xff99ff, 0, 92);
		cells[8][2] = new Cell(0xcc0000, 0, 93);
		cells[8][3] = new Cell(0xff99ff, 0, 94);
		cells[8][4] = new Cell(0xcc0000, 0, 95);
		cells[8][5] = new Cell(0xff99ff, 0, 96);
		cells[8][6] = new Cell(0xcc0000, 0, 97);
		cells[8][7] = new Cell(0xff99ff, 0, 98);
		cells[8][8] = new Cell(0xcc0000, 0, 99);

		for (int i = 0; i < figures.length; i++) {
			for (int j = 0; j < figures[i].length; j++) {
				figures[i][j] = null;
			}
		}

		for (int i = 0; i < aura.length; i++) {
			for (int j = 0; j < aura[i].length; j++) {
				aura[i][j] = false;
			}
		}
	}

	private Figure lineUpSelection() {
		for (Enemies key : lineups.keySet()) {
			for (Figure figure : lineups.get(key)) {
				if (figure != null && figure.isSelected() == true) {
					return figure;
				}
			}
		}

		return null;
	}

	private void unselectAll() {
		for (Figure figure : pile[Enemies.DARK.index()]) {
			figure.unselect();
		}
		for (Figure figure : pile[Enemies.LIGHT.index()]) {
			figure.unselect();
		}
	}

	/**
	 * Cells where the figure can be placed.
	 */
	private boolean[][] placement(Figure figure) {
		boolean places[][] = new boolean[SIZE][SIZE];
		return places;
	}

	/**
	 * Search for win combination according player color.
	 * 
	 * @param enemy
	 * @return
	 */
	private boolean fiveInRow(Enemies enemy) {
		// TODO Search for.
		return false;
	}

	/**
	 * Check for win combination possibility.
	 * 
	 * @param enemy
	 * @return
	 */
	private boolean hasMoreThanFour(Enemies enemy) {
		// TODO Search for.
		return false;
	}

	public Figure[][] getFigures() {
		return figures;
	}

	public Map<Enemies, Vector<Figure>> getLineups() {
		return lineups;
	}

	public void lineUpClick(int index, Enemies enemy) {
		Figure figure = lineups.get(enemy).elementAt(index);

		if (figure == null) {
			return;
		}

		/*
		 * Block all players except the player who turn is.
		 */
		if (turn % Enemies.values().length != figure.getEnemy().index()) {
			return;
		}

		if (figure.isSelected() == true) {
			figure.unselect();
		} else if (figure.isUnselected() == true) {
			unselectAll();
			figure.select();
		}
	}

	public void fieldClick(int i, int j) {
		Figure figure = figures[i][j];

		if (figure == null) {
			// TODO If selected in line-up then place it in the playing field
			// (check is it possible to be placed).
			Figure selected = lineUpSelection();
			if (selected == null) {
				return;
			} else {
				// TODO Check for possible placement.

				/*
				 * Place it in the playing field.
				 */
				figures[i][j] = selected;

				/*
				 * Remove from line-up.
				 */
				for (Enemies key : lineups.keySet()) {
					if (lineups.get(key).contains(selected) == true) {
						int index = lineups.get(key).indexOf(selected);
						lineups.get(key).remove(selected);
						lineups.get(key).insertElementAt(null, index);
						selected.unselect();

						// TODO Handle aura if needed.

						turn++;

						return;
					}
				}

				return;
			}

			// TODO If selected in the playing field then move it (take enemy's
			// figure).
		}

		/*
		 * Block all players except the player who turn is.
		 */
		if (turn % Enemies.values().length != figure.getEnemy().index()) {
			return;
		}

		/*
		 * Select figure in the playing field.
		 */
		unselectAll();
		figure.select();
	}
}
