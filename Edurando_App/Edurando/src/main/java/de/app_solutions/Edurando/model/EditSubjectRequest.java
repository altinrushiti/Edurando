package de.app_solutions.Edurando.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EditSubjectRequest {


    private final Long userId;
    private final String subject;
    private final String topic;



}
