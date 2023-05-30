package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class SubjectRepositoryTest {

    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void testFindByname(){

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);


        Optional<Subject> foundSubject = subjectRepository.findByName("Math");

        assertTrue(foundSubject.isPresent());
        assertEquals(subject.getId(),foundSubject.get().getId());
        assertEquals(subject.getName(), foundSubject.get().getName());
    }


    @Test
    public void testFindAllByName() {

        Subject subject1 = new Subject();
        subject1.setName("Mathematics");
        subjectRepository.save(subject1);

        Subject subject2 = new Subject();
        subject2.setName("Physics");
        subjectRepository.save(subject2);

        Subject subject3 = new Subject();
        subject3.setName("Mathematics");
        subjectRepository.save(subject3);


        List<Subject> foundSubjects = subjectRepository.findAllByName("Mathematics");


        assertEquals(2, foundSubjects.size());

    }
}
