package model.solver;

import goomba.model.VacuumResult;
import goomba.model.solver.Coords;
import goomba.model.solver.Goomba;
import goomba.model.solver.Room;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by Gino on 13/04/2017.
 */
public class GoombaTest {

    private final Goomba testGoomba = new Goomba();
    private Room room;

    private static final String TEST_INPUT_1 = "NNENEWNWNSNEW";


    @Test
    public void test_Goomba_doesNot_goOutOfBounds() throws Exception {
        room = new Room(3, 3, new HashSet<>());
        Coords startPosition = new Coords(1, 1);

        VacuumResult result = testGoomba.go(startPosition, room, "NNN");
        Assert.assertArrayEquals(new int[]{1, 2}, result.getCoords());

        result = testGoomba.go(startPosition, room, "SSS");
        Assert.assertArrayEquals(new int[]{1, 0}, result.getCoords());

        result = testGoomba.go(startPosition, room, "EEE");
        Assert.assertArrayEquals(new int[]{2, 1}, result.getCoords());

        result = testGoomba.go(startPosition, room, "WWWW");
        Assert.assertArrayEquals(new int[]{0, 1}, result.getCoords());
    }

    @Test
    public void go() throws Exception {
        HashSet<Coords> dirtPatches = new HashSet<>();
        dirtPatches.add(new Coords(2 , 3));
        dirtPatches.add(new Coords(4 , 4));
        dirtPatches.add(new Coords(2 , 5));
        dirtPatches.add(new Coords(1 , 5));

        room = new Room(4, 6, dirtPatches);
        VacuumResult result = testGoomba.go(new Coords(0, 0), room, TEST_INPUT_1);
        Assert.assertEquals(2, result.getPatches());
    }

}