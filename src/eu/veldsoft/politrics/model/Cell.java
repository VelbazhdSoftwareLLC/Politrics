package eu.veldsoft.politrics.model;

class Cell {
	private int color;

	private int points;
	
	private int coordinates;
	
	public Cell(int color, int points, int coordinates) {
		super();
		
		this.color = color;
		this.points = points;
		this.coordinates = coordinates;
	}
	
	public int getColor() {
		return color;
	}

	public int getPoints() {
		return points;
	}

	public int getCoordinates() {
		return coordinates;
	}
}
