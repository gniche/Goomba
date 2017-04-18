package goomba.controller;

import goomba.controller.exception.ErrorResponse;
import goomba.controller.exception.InvalidRequestException;
import goomba.model.*;
import goomba.model.solver.Coords;
import goomba.model.solver.Goomba;
import goomba.model.solver.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gino on 15/04/2017.
 */
@RestController
public class VacuumController {

    @Autowired
    Goomba goomba;

    @RequestMapping(value = "/vacuum/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<VacuumResult> vacuum(@RequestBody VacuumRequest vacuumRequest) throws InvalidRequestException {

        checkJSONFieldsValid(vacuumRequest);
        Set<Coords> patches = toPatchCoords(vacuumRequest.getPatches());

        Coords goombaStart = new Coords(vacuumRequest.getCoords()[0], vacuumRequest.getCoords()[1]);
        Room room = new Room(vacuumRequest.getRoomSize()[0], vacuumRequest.getRoomSize()[1], patches);
        VacuumResult result = goomba.go(goombaStart, room, vacuumRequest.getInstructions());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(InvalidRequestException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        error.setInvalidFields(ex.getInvalidFields());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    private static void checkJSONFieldsValid(VacuumRequest vacuumRequest) throws InvalidRequestException {
        Set<String> invalidFields = new HashSet<>();
        if (vacuumRequest.getRoomSize() == null || vacuumRequest.getRoomSize().length != 2) {
            invalidFields.add("roomSize");
        }
        if (vacuumRequest.getPatches() == null) {
            invalidFields.add("patches");
        }
        if (vacuumRequest.getCoords() == null || vacuumRequest.getCoords().length != 2) {
            invalidFields.add("coords");
        }
        if (invalidFields.size() != 0) {
            throw new InvalidRequestException("Some Fields are missing or wrong length", invalidFields);
        }
    }

    private static Set<Coords> toPatchCoords(int[][] patches) throws InvalidRequestException {
        Set<Coords> patchCoords = new HashSet<>(1);
        for (int i = 0; i < patches.length ; i++) {
            if (patches[i].length != 2){
                throw new InvalidRequestException("Array wrong length, must be length 2.", "patch#" + i);
            }
            patchCoords.add(new Coords(patches[i][0], patches[i][1]));
        }
        return patchCoords;
    }
}