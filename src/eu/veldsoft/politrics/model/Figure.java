package eu.veldsoft.politrics.model;

abstract class Figure {

	protected char mark = '\0';

	protected int color = 0;

	protected boolean moving[][] = { 
			{ false, false, false },
			{ false, false, false }, 
			{ false, false, false }, 
	};

	protected boolean beating[][] = { 
			{ false, false, false, false, false },
			{ false, false, false, false, false },
			{ false, false, false, false, false },
			{ false, false, false, false, false },
			{ false, false, false, false, false },
	};

	boolean canStepIn(int coordinates) {
		return true;
	}
}
