package goomba.controller;

import goomba.model.Coords;
import goomba.model.Goomba;
import goomba.model.Room;
import goomba.model.VacuumResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gino on 15/04/2017.
 */
@RestController
public class VacuumController {

    @Autowired
    Goomba goomba;


    public VacuumResult vacuum(
            @RequestParam(value = "roomSize") int[] roomSize,
            @RequestParam(value = "coords") int[] coords,
            @RequestParam(value = "patches") int[][] patches,
            @RequestParam(value = "instructions", defaultValue = "") String instructions) {
        Coords goombaStart = arrayToCoords(coords);
        Room room = toRoom(roomSize, patches);
        return goomba.go(goombaStart, room, instructions);
    }

    private static Room toRoom(int[] roomSize, int[][] patches) {
        if (roomSize == null || roomSize.length != 2) {
            throw new RestClientException("Sucks to be you");
        }
        return new Room(roomSize[0], roomSize[1], toPatchList(patches));
    }

    private static Set<Coords> toPatchList(int[][] patches) {
        if (patches == null) {
            throw new RestClientException("Sucks to be you");
        }
        Set<Coords> patchCoords = new HashSet<>(1);
        for (int[] patchArr : patches) {
            patchCoords.add(arrayToCoords(patchArr));
        }
        return patchCoords;
    }

    private static Coords arrayToCoords(int[] coords) {
        if (coords == null || coords.length != 2) {
            throw new RestClientException("Sucks to be you");
        }
        return new Coords(coords[0], coords[1]);
    }

}
