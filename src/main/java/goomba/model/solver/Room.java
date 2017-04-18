package goomba.model.solver;

import java.util.Set;

/**
 * Created by Gino on 13/04/2017.
 */
public class Room {

    private final int xSize;
    private final int ySize;
    private final Set<Coords> dirtPatches;

    public Room(int xSize, int ySize, Set<Coords> dirtPatches) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.dirtPatches = dirtPatches;
    }

    public boolean hoover(Coords coords) {
        return dirtPatches.contains(coords);
    }

    public int getYSize() {
        return ySize;
    }

    public int getXSize() {
        return xSize;
    }
}
