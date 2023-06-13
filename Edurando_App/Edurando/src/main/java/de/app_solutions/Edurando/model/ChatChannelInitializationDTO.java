package de.app_solutions.Edurando.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatChannelInitializationDTO {

    private Long userIdOne;

    private Long userIdTwo;

}
