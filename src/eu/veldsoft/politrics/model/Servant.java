package eu.veldsoft.politrics.model;

class Servant extends Figure {
	/* Initialize object fields. */{
		mark = 'C';
	}

	Servant(Enemies enemy) {
		this.enemy = enemy;
	}
}
