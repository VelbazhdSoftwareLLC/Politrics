package eu.veldsoft.politrics.model;

public enum Enemy {
	DARK(0, 0x6633cc), LIGHT(1, 0xff6600);
	
	private int index;

	private int color;

	private Enemy(int index, int color) {
		this.index = index;
		this.color = color;
	}

	public int color() {
		return color;
	}

	public int index() {
		return index;
	}

	public static Enemy value(int index) {
		if (index < 0 || index >= values().length) {
			return null;
		}

		int i = 0;
		for (Enemy enemy : values()) {
			if (index == i) {
				return enemy;
			}

			i++;
		}

		return null;
	}

	public Enemy opponent() {
		if (this == DARK) {
			return LIGHT;
		}

		if (this == LIGHT) {
			return DARK;
		}

		return null;
	}

	@Override
	public String toString() {
		return "Enemy [index=" + index + ", color=" + color + "]";
	}
}
