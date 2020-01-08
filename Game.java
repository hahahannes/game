package TerminalGame;

import java.util.Iterator;
import java.util.List;

public class Game {

	private Level level;

	public Game() {
		int width = (int)(Math.floor(Math.random() * (15 -10 +1) +10));
		int height = (int)(Math.floor(Math.random() * (15 -10 +1) +10));

		// TODO: Level erstellen und Wände hinzufügen solange es keinen Weg von Start zu Ziel gibt
		this.level = new Level(width, height);
		this.addWalls();
		/*
		boolean foundPath = true;
		Level lastLevel = new Level(width, height);
		while(foundPath) {
			///lastLevel = level;
			for(int i=0; i<height; i++) {
				for(int l=0; l<width; l++) {
					Field field = this.level.getField(l, i);
					Field newField = lastLevel.getField(l, i);
					if(field.isStart()) {
						newField.markAsStart();
					} else if (field.isTarget()) {
						newField.markAsTarget();
					} else if (field.isWall()) {
						newField.markAsWall();
					}
				}
			}
			this.addWalls();
			foundPath = this.level.existsPathFromAToB(this.level.getStart(), this.level.getTarget());
		}
		System.out.println(lastLevel.toString());
		System.out.println(this.level.toString());

		this.level = lastLevel;
		*/
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
