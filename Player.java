package TerminalGame;

public class Player implements MovableObject {

	private int x;
	private int y;
	private Level level;
	
	public Player(int x, int y, Level level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}
	
	public Field getField() {
		return level.getField(x, y);
	}

	@Override 
	public String toString() {
		return "P";
	}

	public void respawnPlayer() {
		// TODO: implementieren
	}
	
	@Override
	public void moveToNextField() {
		// TODO: implementieren 
	}
	
}
