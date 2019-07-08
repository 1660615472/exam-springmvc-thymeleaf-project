package com.cheer.exam.service;
import com.cheer.exam.model.Question;

import java.util.List;

public interface QuestionService {
    int insert(Question question);
    List<Question> getQuestionList();

    List<String> getKey();
}
