package eu.veldsoft.politrics.model;

class President extends Figure {
	private static final Integer RANGE[] = { 22, 23, 24, 25, 26, 27, 28, 32,
			33, 34, 35, 36, 37, 38, 42, 43, 44, 45, 46, 47, 48, 52, 53, 54, 55,
			56, 57, 58, 62, 63, 64, 65, 66, 67, 68, 72, 73, 74, 75, 76, 77, 78,
			82, 83, 84, 85, 86, 87, 88 };

	/* Initialize object fields. */ {
		mark = 'P';
		
		moving = new boolean [][]{ 
				{ true, true, true },
				{ true, false, true }, 
				{ true, true, true }, 
		};
	}
	
	President(Enemies enemy) {
		this.enemy = enemy;
	}
	
	@Override
	boolean canStepIn(int coordinates) {
		for (Integer value : RANGE) {
			if (value.intValue() == coordinates) {
				return true;
			}
		}

		return false;
	}
}
