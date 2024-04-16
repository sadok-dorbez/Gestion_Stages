package com.example.iatstages.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ResponseUser")
public class ResponseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correct")
    private Boolean correct;

    @Column(name = "response_text")
    private String responseText;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id")
    private Response response;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }


    // Remove the setQuestionId method
    // public void setQuestionId(Long questionId) {
    //     this.questionId = questionId;
    // }
}
