package com.example.iatstages.services.ResponseService;

import com.example.iatstages.entities.Question;
import com.example.iatstages.entities.ResponseUser;
import com.example.iatstages.services.QuestionService.questionService;
import com.example.iatstages.repositories.ResponseUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseUserService {

    private final ResponseUserRepo responseUserRepo;
    private final questionService questionService;


    @Autowired
    public ResponseUserService(ResponseUserRepo responseUserRepo, questionService questionService) {
        this.responseUserRepo = responseUserRepo;
        this.questionService = questionService;
    }


//    public ResponseUser saveResponseUser(ResponseUser responseUser) {
//        return responseUserRepo.save(responseUser);
//    }
//    public boolean compareUserResponses(Long testId, List<ResponseUser> userResponses) {
//        List<Question> questions = questionService.getQuestionsByTestId(testId);
//        if (questions.size() != userResponses.size()) {
//            return false;
//        }
//
//        int correctCount = 0;
//        for (int i = 0; i < questions.size(); i++) {
//            Question question = questions.get(i);
//            ResponseUser userResponse = userResponses.get(i);
//            if (isResponseCorrect(question, userResponse)) {
//                correctCount++;
//            }
//        }
//
//        // Succes rate 70%
//        double successRate = (double) correctCount / questions.size();
//        return successRate >= 0.7;
//    }


//    private boolean isResponseCorrect(Question question, ResponseUser userResponse) {
//        List<Response> correctResponses = question.getResponses().stream()
//                .filter(Response::isCorrect).toList();
//        return correctResponses.stream()
//                .anyMatch(response -> response.getResponseText().equals(userResponse.getResponse().getResponseText()));
//    }




    public ResponseUser saveResponseUser(Long questionId, String responseText) {
        Question question = questionService.getQuestionById(questionId);
        ResponseUser responseUser = new ResponseUser();
        responseUser.setQuestion(question);
        responseUser.setResponseText(responseText);


        responseUser.setCorrect(false);

        if (question.getResponseCorrect().equals(responseText)) {
            responseUser.setCorrect(true);
        }


        return responseUserRepo.save(responseUser);
    }

//    public double calculateCorrectPercentage(Long testId, List<ResponseUser> responseUsers) {
//        long totalResponses = responseUsers.size();
//        long correctResponses = responseUsers.stream()
//                .filter(responseUser -> responseUser.getQuestion().getTest().getId().equals(testId) && responseUser.getCorrect())
//                .count();
//
//        // Calculate the percentage
//        return (double) correctResponses / totalResponses * 100;
//    }

          public int countCorrectResponseUsersByTestId(Long testId) {
          List<ResponseUser> correctResponseUsers = getCorrectResponseUsersByTestId(testId);
              return correctResponseUsers.size();
            }

    public List<ResponseUser> getCorrectResponseUsersByTestId(Long testId) {
        return responseUserRepo.findCorrectResponseUsersByTestId(testId);
    }

   public double calculateCorrectResponsePercentageByTestId(Long testId) {
        int totalQuestions = questionService.countQuestionsByTestId(testId);
        int correctResponses = countCorrectResponseUsersByTestId(testId);
        return (double) correctResponses / totalQuestions * 100;
    }





//    public List<ResponseUser> saveMultipleResponseUsers(List<ResponseUser> responseUsers) {
//        return responseUsers.stream()
//                .map(responseUser -> saveResponseUser(responseUser))
//                .collect(Collectors.toList());
//    }





}
