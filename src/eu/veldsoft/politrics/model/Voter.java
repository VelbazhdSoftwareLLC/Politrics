package eu.veldsoft.politrics.model;

class Voter extends Figure {
	/* Initialize object fields. */ {
		mark = 'V';
		
		moving = new boolean[][] { 
				{ true, true, true },
				{ true, false, true }, 
				{ true, true, true }, 
		};
		
		beating = new boolean[][] { 
				{ true, false, true, false, true },
				{ false, false, false, false, false },
				{ true, false, false, false, true },
				{ false, false, false, false, false },
				{ true, false, true, false, true },
		};
	}
	
	Voter(Enemies enemy) {
		this.enemy = enemy;
	}
}
