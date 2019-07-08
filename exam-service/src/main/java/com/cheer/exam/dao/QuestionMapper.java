package com.cheer.exam.dao;
import com.cheer.exam.model.Question;

import java.util.List;

public interface QuestionMapper {
   int insertQuestion(Question subject);//插入考题方法

   List<Question> getQuestionList();//返回答案list结合方法
   List<String> getKey();

   int update(Question question);
}
