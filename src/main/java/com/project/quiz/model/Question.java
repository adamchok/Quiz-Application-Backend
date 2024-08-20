package com.project.quiz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "option_1")
    private String option_1;

    @Column(name = "option_2")
    private String option_2;

    @Column(name = "option_3")
    private String option_3;

    @Column(name = "option_4")
    private String option_4;

    @Column(name = "answer")
    private String answer;

    @Column(name = "difficulty_level")
    private String difficulty_level;

}
