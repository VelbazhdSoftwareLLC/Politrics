package eu.veldsoft.politrics.model;

public abstract class Figure {
	protected char mark = '\0';

	protected Enemies enemy = null;

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

	public char getMark() {
		return mark;
	}

	public Enemies getEnemy() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enemy == null) ? 0 : enemy.hashCode());
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
		if (mark != other.mark)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Figure [mark=" + mark + ", enemy=" + enemy + "]";
	}
}
