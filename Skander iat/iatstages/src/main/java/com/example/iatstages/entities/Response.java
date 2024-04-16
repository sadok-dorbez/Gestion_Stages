package com.example.iatstages.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Response")
public class Response {
    @Id
    //@Column(name = "responseId")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long responseId ;
    @Basic
    @Column(name = "Response")
    private String responseText ;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonBackReference
    private Question question;
    @Column(name = "is_Correct")
    private Boolean isCorrect;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Boolean isCorrect() {
        return isCorrect;
    }

    public Long getResponseId() {
        return responseId;
    }

}
