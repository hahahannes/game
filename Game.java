package TerminalGame;

import java.util.Iterator;

public class Game {

	private Level level;

	public Game() {
		int width = (int)(Math.floor(Math.random() * (15 -10 +1) +10));
		int height = (int)(Math.floor(Math.random() * (15 -10 +1) +10));

		// TODO: Level erstellen und Wände hinzufügen solange es keinen Weg von Start zu Ziel gibt
		boolean foundPath = false;
		while(!foundPath) {
			level = new Level(width, height);
			this.addWalls();
			foundPath = this.level.existsPathFromAToB(this.level.getStart(), this.level.getTarget());
		}
	}
	
	private void addWalls() {
		// TODO: implementieren
		Iterator<Field> iterator = this.level.iterator();
		while (iterator.hasNext()) {
			Field field = iterator.next();
			if(!field.isStart() && !field.isTarget() && !field.isWall()) {
				double random = Math.random();
				if(random < 0.33) {
					field.markAsWall();
				}
			}
		}
	}

	public void startGame() {
		level.spawnEntities();
		System.out.println("Start:");
		System.out.println(level);
		int i = 1;
		while(level.getPlayer().getField() != level.getTarget()) {
			level.updateEntities();
			System.out.println("Step " + i);
			i++;
			System.out.println(level);
		}
		System.out.println("Player has won the game!");
	}

}
