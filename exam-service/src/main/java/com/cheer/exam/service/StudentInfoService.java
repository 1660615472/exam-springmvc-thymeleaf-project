package com.cheer.exam.service;


import com.cheer.exam.model.Student;

public interface StudentInfoService {
    Student getStudentInfo(Integer ecn);

    boolean checkStudentLogin(Integer ecn, String password);

    int update(Student studentInfo);
}
