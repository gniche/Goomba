package goomba.model;

import goomba.model.solver.Coords;

/**
 * Created by Gino on 13/04/2017.
 */
public class VacuumResult {

    private final int[] coords;
    private final int patches;

    public VacuumResult(Coords finalPosition, int patches) {
        this.coords = new int[]{finalPosition.getX(), finalPosition.getY()};
        this.patches = patches;
    }

    public int[] getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }
}
