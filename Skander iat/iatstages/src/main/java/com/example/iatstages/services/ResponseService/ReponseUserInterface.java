package com.example.iatstages.services.ResponseService;
import com.example.iatstages.entities.Question;
import com.example.iatstages.entities.Response;
import com.example.iatstages.entities.ResponseUser;


import java.util.List;

public interface ReponseUserInterface {


    ResponseUser saveResponseUser(ResponseUser responseUser);


    List<ResponseUser> getResponsesForQuestion(Question question);


    boolean compareUserResponses(Long testId, List<ResponseUser> userResponses);

    Response getCorrectResponseByQuestionId(Long questionId);
}
