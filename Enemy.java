package TerminalGame;

import java.util.List;
import java.util.Random;

public class Enemy implements MovableObject {

	private Level level;
	private int x;
	private int y;
	
	public Enemy(int x, int y, Level level) {
		this.level = level;
		this.x = x;
		this.y = y;
	}

	public Field getField() {
		return level.getField(x, y);
	}

	@Override
	public void moveToNextField() {
		Field nextField;
		Random rand = new Random();
		List<Field> neighborhood = level.getNeighbors(level.getField(x, y));
		int number, temps = 0;
		if(neighborhood.size() == 0) {
			return;
		}
		do {
			number = rand.nextInt(neighborhood.size());
			temps++;
		} while((neighborhood.get(number).isWall() || neighborhood.get(number) == level.getField(x, y))&& temps < 20 );
		if(temps != 20) {
			nextField =neighborhood.get(number);

			level.getField(x, y).setMovableObject(null);
			if(nextField.getMovableObject() instanceof Player) {
				level.getPlayer().respawnPlayer();
			}
			nextField.setMovableObject(this);
			x = nextField.getX();
			y = nextField.getY();
		}
	}
	
	@Override 
	public String toString() {
		return "E";
	}

}
