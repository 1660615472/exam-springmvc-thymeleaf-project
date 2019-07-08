package com.cheer.exam.service.impl;
import com.cheer.exam.dao.QuestionMapper;
import com.cheer.exam.model.Question;
import com.cheer.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional //声明事务 将该类下所有的共有方法设置为事务方法
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public int insert(Question question) {
        int i = this.questionMapper.insertQuestion ( question );
        if (i > 0) {
            System.out.println ( "插入成功!" );
        } else {
            System.out.println ( "插入失败!" );
        }
        return i;
    }

    @Override
    public List<Question> getQuestionList() {
        List<Question> subjectList=this.questionMapper.getQuestionList ();
        return  subjectList;
    }



    @Override
    public List<String> getKey() {
        List<String> keyList=this.questionMapper.getKey ();
        return keyList;
    }
}
