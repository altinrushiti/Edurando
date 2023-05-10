package de.app_solutions.Edurando.controller;

import de.app_solutions.Edurando.model.EditSubjectRequest;
import de.app_solutions.Edurando.model.EditTopicRequest;
import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.repository.SubjectRepository;
import de.app_solutions.Edurando.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
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

    @PostMapping("/updateSubjectData")
    public String updateSubjectData(@RequestBody EditSubjectRequest editSubjectRequest) {
        System.out.println(editSubjectRequest);
        return subjectService.updateSubjectData(editSubjectRequest);

    }


}
