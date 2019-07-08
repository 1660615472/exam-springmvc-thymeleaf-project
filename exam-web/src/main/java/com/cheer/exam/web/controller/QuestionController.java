package com.cheer.exam.web.controller;

import com.cheer.exam.model.Question;
import com.cheer.exam.service.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
@Log4j2
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping("questionList1")
    public List questionList(){
        List<Question> questionList = this.questionService.getQuestionList ();
        if(questionList!=null) {
         log.debug ( "所有题目找到了》》》》》》》》》》》》》" );
        }
        return questionList;
    }
}
