package com.example.iatstages.services.QuestionService;

import com.example.iatstages.entities.Question;


import java.util.List;

public interface questionInterface {

    List<Question> getAllQuestions();
    List<Question> getQuestionsByTestId(Long testId);
    Question getQuestionById(Long questionId);
    Question createQuestion(Long testId, Question question);

    Question updateQuestion(Long id, Question updatedQuestion);
    void deleteQuestion(Long id);
}
