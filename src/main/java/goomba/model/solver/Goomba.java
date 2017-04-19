package goomba.model.solver;

import goomba.model.VacuumResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Goomba is your new favourite household pet, just set Goomba down in any room with some gentle direction
 * and in no time at all Goomba will clean up up you mess!
 */
@Service
public class Goomba {

    private static Logger logger = LoggerFactory.getLogger(Goomba.class);

    /**
     * Go is the service method to vacuum a room
     *
     * @return VacuumResult containing the final position and th number of dirt patches vacuumed up
     */
    public VacuumResult go(Coords startPosition, Room room, String instructions) {
        Coords currentPosition = startPosition;
        logger.info("Goomba starting vacuum from startPosition: {}", currentPosition);
        Set<Coords> vacuumed = new HashSet<>();
        hoover(currentPosition, room, vacuumed);
        for (char direction : instructions.toUpperCase().toCharArray()) {
            switch (direction) {
                case 'N':
                    currentPosition = calculateYPosition(currentPosition, 1, room);
                    break;
                case 'S':
                    currentPosition = calculateYPosition(currentPosition, -1, room);
                    break;
                case 'E':
                    currentPosition = calculateXPosition(currentPosition, 1, room);
                    break;
                case 'W':
                    currentPosition = calculateXPosition(currentPosition, -1, room);
                    break;
                default:
                    logger.warn("Invalid input: '{}'. ignoring...", direction);
            }
            logger.debug("Goomba currentPosition: {}", currentPosition);
            hoover(currentPosition, room, vacuumed);
        }
        logger.info("Goomba finished vacuuming room, returning result.");
        return new VacuumResult(currentPosition, vacuumed.size());
    }

    private static void hoover(Coords currentPosition, Room room, Set<Coords> vacuumed) {
        if (room.hoover(currentPosition)) {
            logger.debug("found some dirt!");
            vacuumed.add(currentPosition);
        }
    }

    private static Coords calculateXPosition(Coords currentPosition, int movement, Room room) {
        int xCoord = calculatePosition(currentPosition.getX(), movement, room.getXSize());
        return new Coords(xCoord, currentPosition.getY());
    }

    private static Coords calculateYPosition(Coords currentPosition, int movement, Room room) {
        int yCoord = calculatePosition(currentPosition.getY(), movement, room.getYSize());
        return new Coords(currentPosition.getX(), yCoord);
    }

    private static int calculatePosition(int coord, int movement, int roomSize) {
        int coordNew = coord + movement;
        if (coordNew < 0) {
            logger.debug("Goomba skids into the wall!");
            return 0;
        } else if (coordNew >= roomSize) {
            logger.debug("Goomba skids into the wall!");
            return roomSize - 1;
        } else {
            logger.debug("Goomba keeps on truckin'!");
            return coordNew;
        }
    }
}