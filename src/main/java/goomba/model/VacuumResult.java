package goomba.model;

/**
 * Created by Gino on 13/04/2017.
 */
public class VacuumResult {

    private final Coords finalPosition;
    private final int patches;

    public VacuumResult(Coords finalPosition, int patches) {
        this.finalPosition = finalPosition;
        this.patches = patches;
    }

    public Coords getFinalPosition() {
        return finalPosition;
    }

    public int getPatches() {
        return patches;
    }
}
