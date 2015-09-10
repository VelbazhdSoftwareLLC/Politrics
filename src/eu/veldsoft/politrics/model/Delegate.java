package eu.veldsoft.politrics.model;

class Delegate extends Figure {
	/* Initialize object fields. */ {
		mark = 'V';
		
		moving = new boolean[][] { 
				{ false, true, false },
				{ true, false, true }, 
				{ false, true, false }, 
		};
		
		beating = new boolean[][] { 
				{ false, false, true, false, false },
				{ false, false, false, false, false },
				{ true, false, false, false, true },
				{ false, false, false, false, false },
				{ false, false, true, false, false },
		};
	}
	
	Delegate(int color) {
		this.color = color;
	}
}
