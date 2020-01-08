package TerminalGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Level implements Iterable {

	private Field[][] fields;
	private int width;
	private int height;
	private Field start;
	private Field target;
	private Player player;
	private List<MovableObject> entities;
	
	public Level(int width, int height) {
		this.width = width;
		if(this.width < 3) {
			this.width = 3;
		}
		this.height = height;
		if(this.height < 3) {
			this.height = 3;
		}
		if(this.width > 0 && this.height > 0) {
			fields = new Field[this.height][this.width];
			for(int i = 0; i < this.height; i++) {
				for( int j = 0; j < this.width; j++) {
					fields[i][j] = new Field(j,i);
					if(j == 0 || j == this.width - 1 || i == 0 || i == this.height - 1) {
						fields[i][j].markAsWall();
					}
				}
			}
		}
		int x = 0;
		int y = 0;
		do {
			y = (int)(Math.floor(Math.random() * ((height-2) -1 +1) +1));
			x = (int)(Math.floor(Math.random() * ((width-2) -1 +1) +1));
		}while(getField(x,y).isWall());
		getField(x, y).markAsTarget();
		target = getField(x, y);
		do {
			y = (int)(Math.floor(Math.random() * ((height-2) -1 +1) +1));
			x = (int)(Math.floor(Math.random() * ((width-2) -1 +1) +1));
		}while(getField(x,y).isWall() || getField(x, y).isTarget());
		getField(x, y).markAsStart();
		start = getField(x, y);
	}
	
	public Field getStart() {
		return start;
	}

	public Field getTarget() {
		return target;
	}

	public Field getField(int x, int y) {
		if(x >= 0 && x < this.width && y >= 0 && y < this.height) {
			return fields[y][x];
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		String level = "";
		for(int i = 0; i < this.height; i++) {
			for( int j = 0; j < this.width; j++) {
				level += fields[i][j].fieldTypeToString();
			}
			level += "\n";
		}
		return level;
	}

	public List<Field> getNeighbors(Field field) {
		List<Field> result = new ArrayList<Field>();
		int relativeIndices[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		for (int[] delta: relativeIndices) {
			int columnIndex = field.getX() + delta[0];
			int rowIndex = field.getY() + delta[1];
			if(columnIndex > 0 && columnIndex < width-1 && rowIndex > 0 && rowIndex < height-1) {
				result.add(fields[rowIndex][columnIndex]);
			}
		}
		return result;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void spawnEntities() {
		entities = new ArrayList<MovableObject>();
		player = new Player(start.getX(),start.getY(),this);
		start.setMovableObject(player);
		entities.add(player);

		int y,x;
		do {
			y = (int)(Math.floor(Math.random() * ((height-2) -1 +1) +1));
			x = (int)(Math.floor(Math.random() * ((width-2) -1 +1) +1));
		}while(getField(x,y).isWall() || getField(x, y).isTarget() || getField(x,y).isStart());
		Enemy e1 = new Enemy(x,y,this);
		getField(x, y).setMovableObject(e1);
		entities.add(e1);
		
		do {
			y = (int)(Math.floor(Math.random() * ((height-2) -1 +1) +1));
			x = (int)(Math.floor(Math.random() * ((width-2) -1 +1) +1));
		}while(getField(x,y).isWall() || getField(x, y).isTarget() || getField(x,y).isStart() || getField(x, y).getMovableObject() != null);
		Enemy e2 = new Enemy(x,y,this);
		getField(x, y).setMovableObject(e2);
		entities.add(e2);
	}

	public void updateEntities() {
		for(MovableObject e : entities) {
			e.moveToNextField();
		}
	}

	public boolean existsPathFromAToB(Field a, Field b) {
		return this.findPathFromAToB(a, b) != null;
	}

	
	public List<Field> findPathFromAToB(Field a, Field b) {
		List<Field> path = this.findPathFromAToB(a, b, new ArrayList<Field>());
		if(path.isEmpty()) {
			return null;
		} 
		path.add(b);
		return path;
	}

	
	private List<Field> findPathFromAToB(Field a, Field b, Collection<Field> markedFields) {
		List<Field> n = this.getNeighbors(b);
		List<Field> candidates = new ArrayList<Field>();
		for(int i=0; i<n.size(); i++) {
			Field field = n.get(i);
			if(field.isStart()) {
				List<Field> start = new ArrayList<Field>();
				start.add(field);
				return start;
			}
			if(!field.isWall() && !markedFields.contains(field)) {
				candidates.add(field);
			}
			markedFields.add(field);
		}
		if(candidates.isEmpty()) {
			return new ArrayList<Field>();
		}
		
		List<Field> path = new ArrayList<Field>();
		for(int i=0; i<candidates.size(); i++) {
			List<Field> newPath = this.findPathFromAToB(a, candidates.get(i), markedFields);
			if(!newPath.isEmpty()) {
				newPath.add(candidates.get(i));
				path = newPath;
				break;
			}
		}
		return path;
	}

	
	@Override
	public Iterator<Field> iterator() {
		return new FieldIterator(this);
	}
}
