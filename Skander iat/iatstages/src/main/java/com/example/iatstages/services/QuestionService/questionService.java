package com.example.iatstages.services.QuestionService;


import com.example.iatstages.entities.Question;
import com.example.iatstages.entities.Response;
import com.example.iatstages.entities.Test;
import com.example.iatstages.services.ResponseService.responseService;
import com.example.iatstages.repositories.QuestionRepo;
import com.example.iatstages.repositories.TestRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class questionService implements questionInterface {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private TestRepo testRepo;

    @Autowired
    private responseService responseService;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public List<Question> getQuestionsByTestId(Long testId) {
        return questionRepo.findByTestId(testId);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepo.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));
    }



    @Override
    public Question createQuestion(Long testId, Question question) {
        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException("Test not found with id: " + testId));

        if (question.getResponseCorrect() == null) {
            question.setResponseCorrect("No correct response yet");
        }
        question.setTest(test);
        question = questionRepo.save(question);
        List<Response> responsesCopy = new ArrayList<>(question.getResponses());

        for (Response response : responsesCopy) {
            responseService.createResponse(question.getId(), response);
            if (response.getIsCorrect().equals(true)) {
                responseService.updateCorrectResponseForQuestion(response.getResponseId());
            }
        }

        return question;
    }
//    @Override
//    public Response createResponseAndUpdateQuestion(Long questionId, Response answer) {
//        // Check if the question exists
//        Optional<Question> questionOptional = questionRepo.findById(questionId);
//        if (!questionOptional.isPresent()) {
//            throw new EntityNotFoundException("Question not found with id: " + questionId);
//        }
//
//        Response createdAnswer = responseService.createResponse(questionId, answer);
//
//        if (createdAnswer.getIsCorrect() != null && createdAnswer.getIsCorrect().equals(true)) {
//            Question question = questionOptional.get();
//            question.setResponseCorrect(createdAnswer.getResponseText());
//            questionRepo.save(question);
//        }
//
//        return createdAnswer;
//    }






    @Override
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question question = questionRepo.findById(id).orElseThrow();
        question.setQuestionText(updatedQuestion.getQuestionText());
        return questionRepo.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }


    public List<Question> getCorrectAnswersForTest(Long testId) {
        return questionRepo.findCorrectAnswersByTestId(testId);
    }


    public int countQuestionsByTestId(Long testId) {
        List<Question> questions = questionRepo.findByTestId(testId);
        return questions.size();
    }
}
