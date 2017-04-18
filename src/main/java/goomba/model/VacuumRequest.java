package goomba.model;

/**
 * Created by Gino on 17/04/2017.
 */
public class VacuumRequest {

    private int[] coords;
    private int[] roomSize;
    private int[][] patches;
    private String instructions;

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public void setRoomSize(int[] roomSize) {
        this.roomSize = roomSize;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public int[] getCoords() {
        return coords;
    }

    public int[][] getPatches() {
        return patches;
    }

    public void setPatches(int[][] patches) {
        this.patches = patches;
    }
}
