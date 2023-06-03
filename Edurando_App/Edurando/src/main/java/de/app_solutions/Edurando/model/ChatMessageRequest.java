package de.app_solutions.Edurando.model;


public record ChatMessageRequest(
    Long sender,
    Long receiver,
    String content
)
{}
