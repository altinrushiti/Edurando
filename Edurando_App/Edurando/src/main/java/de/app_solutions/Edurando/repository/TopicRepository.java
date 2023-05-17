package de.app_solutions.Edurando.repository;

import de.app_solutions.Edurando.model.Subject;
import de.app_solutions.Edurando.model.Topic;
import de.app_solutions.Edurando.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {


    Optional<Topic> findById(Long id);
    Optional<Topic> findByName(String name);

    /*
    @Query("SELECT userProfiles from Topic where Topic.name = ?1")
    Optional<List<UserProfile>>  findUserProfilesByName(String  name);
*/
/*    @Query(value ="select t.name FROM subject s JOIN subject_topics st ON (s.id =st.topics_id) JOIN topic t ON (st.subject_id=t.id) WHERE s.name = ?1", nativeQuery = true)
    List<String> findTopicsBySubjectName(String  name);
    @Query(value ="select t.name from topic t join subject_topics st on (t.id =st.topics_id) join subject s on (st.subject_id=s.id) where s.name = ?1", nativeQuery = true)
    String findTopicsBySubjectNameString(String  name);*/
}
/*
* @Query(value ="select t.name FROM subject s JOIN subject_topics st ON (s.id =st.topics_id) JOIN topic t ON (st.subject_id=t.id) WHERE s.name = ?1", nativeQuery = true)

 * */