package TerminalGame;

import java.util.Iterator;

public class Game {

	private Level level;

	public Game() {
		int width = (int)(Math.floor(Math.random() * (15 -10 +1) +10));
		int height = (int)(Math.floor(Math.random() * (15 -10 +1) +10));

		// TODO: Level erstellen und Wände hinzufügen solange es keinen Weg von Start zu Ziel gibt
		level = new Level(width, height);
	}
	
	private void addWalls() {
		// TODO: implementieren
		Iterator iterator = this.level.iterator();
		while (iterator.hasNext()) {
			Field field = iterator.next();
			if(!field.isStart() && !field.isTarget() && !field.isWall()) {
				// 1/3 TODO
				field.markAsWall();
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
