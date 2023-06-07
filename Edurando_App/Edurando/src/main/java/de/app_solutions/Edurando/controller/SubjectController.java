package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.repository.SubjectRepository;
import de.app_solutions.Edurando.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/subjects")
    List<Subject> getSubjects(){

        return subjectService.getAllSubjects();
    }

    @GetMapping("/subject/{name}")
    Subject getSubject(@PathVariable String name){

        return subjectService.getSubjectByName(name);
    }

    @PutMapping("/updateSubjectData")
    public ResponseEntity<String> updateSubjectData(@RequestBody EditSubjectRequest editSubjectRequest) {
        Pair<Boolean, String> response = subjectService.addSubjectData(editSubjectRequest);
        if (response.getFirst()) return ResponseEntity.ok(response.getSecond());
        else return ResponseEntity.badRequest().body(response.getSecond());
    }

}
