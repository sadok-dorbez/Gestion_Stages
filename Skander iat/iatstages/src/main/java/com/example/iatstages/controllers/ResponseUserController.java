package com.example.iatstages.controllers;

import com.example.iatstages.entities.ResponseUser;
import com.example.iatstages.services.ResponseService.ResponseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/responseUser")


public class ResponseUserController {

    @Autowired
    private ResponseUserService responseUserService;

    @Autowired
    private com.example.iatstages.services.QuestionService.questionService questionService;

    @Transactional
    @PostMapping("/{questionId}")
    public ResponseEntity<ResponseUser> saveResponseUser(@PathVariable Long questionId, @RequestBody ResponseUser responseUser) {
        String responseText = responseUser.getResponseText();

        ResponseUser savedResponseUser = responseUserService.saveResponseUser(questionId, responseText);
        return new ResponseEntity<>(savedResponseUser, HttpStatus.CREATED);
    }
//    @GetMapping("/calculatePercentage/{testId}")
//    public ResponseEntity<Double> calculateCorrectPercentage(@PathVariable Long testId, @RequestBody List<ResponseUser> responseUsers) {
//        double percentage = responseUserService.calculateCorrectPercentage(testId, responseUsers);
//        return new ResponseEntity<>(percentage, HttpStatus.OK);
//    }

    @GetMapping("/countCorrectByTestId/{testId}")
    public ResponseEntity<Integer> countCorrectResponseUsersByTestId(@PathVariable Long testId) {
        int count = responseUserService.countCorrectResponseUsersByTestId(testId);
       return new ResponseEntity<>(count, HttpStatus.OK); }

    @GetMapping("/correctPercentageByTestId/{testId}")
    public ResponseEntity<Double> getCorrectResponsePercentageByTestId(@PathVariable Long testId) {
     double percentage = responseUserService.calculateCorrectResponsePercentageByTestId(testId);
        return new ResponseEntity<>(percentage, HttpStatus.OK);
   }



//        @PostMapping("/compare/{testId}")
//        public ResponseEntity<Boolean> compareUserResponses(@PathVariable Long testId, @RequestBody List<ResponseUser> userResponses) {
//            boolean result = responseUserService.compareUserResponses(testId, userResponses);
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }



//    @PostMapping("/submitAnswers")
//    public ResponseEntity<List<ResponseUser>> submitAnswers(@RequestBody List<ResponseUser> responseUsers) {
//        List<ResponseUser> savedResponseUsers = responseUserService.saveMultipleResponseUsers(responseUsers);
//        return new ResponseEntity<>(savedResponseUsers, HttpStatus.CREATED);
//    }

}
