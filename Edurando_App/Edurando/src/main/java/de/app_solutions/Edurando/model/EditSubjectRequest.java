package de.app_solutions.Edurando.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EditSubjectRequest {


    private  Long id;
    private  String subject;
    private  String topic;



}
