package eu.veldsoft.politrics.model;

class Minister extends Figure {
	/* Initialize object fields. */{
		mark = 'M';

		moving = new boolean[][] { { true, false, true },
				{ false, false, false }, { true, false, true }, };

		beating = new boolean[][] { { true, false, false, false, true },
				{ false, false, false, false, false },
				{ false, false, false, false, false },
				{ false, false, false, false, false },
				{ true, false, false, false, true }, };
	}

	Minister(Enemy enemy) {
		this.enemy = enemy;
	}
}
