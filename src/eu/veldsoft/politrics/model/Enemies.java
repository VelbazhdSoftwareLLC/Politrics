package eu.veldsoft.politrics.model;

public enum Enemies {
	DARK(0, 0x6633cc), LIGHT(1, 0xff6600);

	private int index;

	private int color;

	private Enemies(int index, int color) {
		this.index = index;
		this.color = color;
	}

	public int color() {
		return color;
	}

	public int index() {
		return index;
	}

	@Override
	public String toString() {
		return "Enemy [index=" + index + ", color=" + color + "]";
	}
}
