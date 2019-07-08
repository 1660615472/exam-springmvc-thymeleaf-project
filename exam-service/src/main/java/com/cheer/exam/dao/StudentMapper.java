package com.cheer.exam.dao;

import com.cheer.exam.model.Student;

public interface StudentMapper {
 Student getStudentOne(Integer ecn);
 int upDateStudnet(Student studentInfo);
}
