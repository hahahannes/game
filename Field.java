package TerminalGame;

public class Field {

	private int x;
	private int y;
	private boolean isStart;
	private boolean isTarget;
	private boolean isWall;
	private MovableObject movableObject = null;
	
	public Field(int x, int y) {
		this.x = x;
		this.y = y;
		this.isStart = false;
		this.isTarget = false;
		this.isWall = false;
	}

	public boolean isStart() {
		return isStart;
	}

	public void markAsStart() {
		this.isStart = true;
	}

	public boolean isTarget() {
		return isTarget;
	}

	public void markAsTarget() {
		this.isTarget = true;
	}

	public boolean isWall() {
		return isWall;
	}

	public void markAsWall() {
		this.isWall = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public MovableObject getMovableObject() {
		return movableObject;
	}
	
	public void setMovableObject(MovableObject movableObject) {
		this.movableObject = movableObject;
	}

	@Override
	public String toString() {
		return "F: <" + fieldTypeToString()+ "> (" + x + ", "+ y+")";
	}
	public String fieldTypeToString() {
		if(isWall) {
			return "W";
		} else if(movableObject != null) {
			return movableObject.toString();
		} else if(isTarget) {
			return "T";
		} else if(isStart) {
			return "S";
		} else {
			return " ";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isStart ? 1231 : 1237);
		result = prime * result + (isTarget ? 1231 : 1237);
		result = prime * result + (isWall ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}
		Field other = (Field) obj;
		if (isStart != other.isStart) {
			return false;
		}
		if (isTarget != other.isTarget) {
			return false;
		}
		if (isWall != other.isWall) {
			return false;
		}
		if (movableObject == null) {
			if (other.movableObject != null) {
				return false;
			}
		} else if (!movableObject.equals(other.movableObject)) {
			return false;
		}
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}


	
}
