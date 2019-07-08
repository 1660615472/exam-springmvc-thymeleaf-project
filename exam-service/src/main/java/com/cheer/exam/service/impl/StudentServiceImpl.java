package com.cheer.exam.service.impl;

import com.cheer.exam.dao.QuestionMapper;
import com.cheer.exam.dao.StudentMapper;
import com.cheer.exam.model.Student;
import com.cheer.exam.model.Student;
import com.cheer.exam.service.StudentInfoService;
import com.cheer.exam.utils.MybatisUtils;
import com.cheer.exam.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*学生信息dao实现类*/
@Transactional //声明事务 将该类下所有的共有方法设置为事务方法
@Service
public class StudentServiceImpl implements StudentInfoService {
    @Autowired
    QuestionMapper QuestionMapper ;
    @Autowired
    StudentMapper studentMapper ;

    //根据准考证号找到对应考试方法
    @Override
    public Student getStudentInfo(Integer ecn) {
        Student studentOne = this.studentMapper.getStudentOne ( ecn );
        return studentOne;
    }

    //检查登录，实现dao接口
    public boolean checkStudentLogin(Integer ecn,String password){
        Student studentOne = this.studentMapper.getStudentOne ( ecn );
        if(studentOne == null){
        return false;
    }

    if(StringUtils.encrypt ( password ).equals ( studentOne.getPassword () )){
        return true;
    }else {
        return false;
    }
    }

    //跟新学生信息 插到数据库方法
    @Override
    public int update(Student studentInfo) {
        int i = this.studentMapper.upDateStudnet ( studentInfo );
        return i;
    }
}
