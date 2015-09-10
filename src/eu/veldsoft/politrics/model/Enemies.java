package eu.veldsoft.politrics.model;

enum Enemies {
	DARK(0x6633cc), LIGHT(0xff6600);
	
	private int color;
	
	private Enemies(int color) {
		this.color = color;
	}
	
	int color() {
		return color;
	}
}
