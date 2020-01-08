package TerminalGame;

import java.util.Iterator;

public class FieldIterator implements Iterator {

    private int x = 0;
    private int y = 0;
    private Level level;

    public FieldIterator(Level level) {
        this.level = level;
    } 

    @Override
    public Field next() {
        /*
        Field[][] fields = this.level.fields;
        for(int i=0; i<fields.length; i++) {
            this.y = i;
            for(int l=0; l<fields[i].length; l++) {
                this.x = l;
                return fields[i][l];
            }
        }*/
        if(this.x+1 == this.level.getWidth()) {
            this.x = 0;
        } else {
            this.x += 1;
        }
        // this.y += 1;
        if(this.x == this.level.getWidth()-1) {
            this.y += 1;
        }
        // return new Field(this.x, this.y)
        return this.level.getField(this.x, this.y);
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = true;
        if(this.y == this.level.getHeight()-1) {
            if(this.x+1 == this.level.getWidth()) {
                hasNext = false;
            }
        }
        return hasNext;
    }
}