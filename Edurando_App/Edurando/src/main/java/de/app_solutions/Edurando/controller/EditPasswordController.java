package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditPasswordRequest;
import de.app_solutions.Edurando.model.EditPersonalDataRequest;
import de.app_solutions.Edurando.service.EditPasswordService;
import de.app_solutions.Edurando.service.EditProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class EditPasswordController {


    private final EditPasswordService editPasswordService;

    @PutMapping("/editPassword")
    public ResponseEntity<String> editPassword(@RequestBody EditPasswordRequest passwordRequest) {

        Pair<Boolean, String> result = editPasswordService.editPassword(passwordRequest);

        if (result.getFirst()) {
            return ResponseEntity.ok().body(result.getSecond());
        } else {
            return ResponseEntity.badRequest().body(result.getSecond());
        }
    }

}

