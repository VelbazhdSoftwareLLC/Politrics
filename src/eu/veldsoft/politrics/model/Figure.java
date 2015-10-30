package eu.veldsoft.politrics.model;

public abstract class Figure {
	protected char mark = '\0';

	protected Enemy enemy = null;

	protected boolean selected = false;

	protected boolean moving[][] = { { false, false, false },
			{ false, false, false }, { false, false, false }, };

	protected boolean beating[][] = { { false, false, false, false, false },
			{ false, false, false, false, false },
			{ false, false, false, false, false },
			{ false, false, false, false, false },
			{ false, false, false, false, false }, };

	boolean canStepIn(int coordinates) {
		return true;
	}

	public boolean canCaptureIn(int dx, int dy) {
		/*
		 * No more than one cell distance.
		 */
		if (dx < -2 || dy < -2 || dx > +2 || dy > +2) {
			return false;
		}

		dx += 2;
		dy += 2;

		return beating[dx][dy];
	}

	boolean canMoveIn(int dx, int dy) {
		/*
		 * No more than one cell distance.
		 */
		if (dx < -1 || dy < -1 || dx > +1 || dy > +1) {
			return false;
		}

		dx++;
		dy++;

		return moving[dx][dy];
	}

	public char getMark() {
		return mark;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isUnselected() {
		return !selected;
	}

	public void select() {
		selected = true;
	}

	public void unselect() {
		selected = false;
	}

	public void invert() {
		selected = !selected;
	}

	public int index() {
		if (this instanceof President) {
			return 0;
		} else if (this instanceof Voter) {
			return 1;
		} else if (this instanceof Minister) {
			return 2;
		} else if (this instanceof Delegate) {
			return 3;
		} else if (this instanceof Servant) {
			return 4;
		}

		return -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enemy == null) ? 0 : enemy.hashCode());
		result = prime * result + super.hashCode();
		result = prime * result + mark;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figure other = (Figure) obj;
		if (enemy != other.enemy)
			return false;
		if (hashCode() != other.hashCode())
			return false;
		if (mark != other.mark)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Figure [mark=" + mark + ", enemy=" + enemy + "]";
	}
}
