package com.cheer.exam.model;

import java.io.Serializable;

/*这是学生信息模板类*/
public class Student implements Serializable {
    private int ecn;//准考证号
    private int yes;//答对数
    private int no; //答错数

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    private String qualified;//是否合格

    public Student(int yes, int no, double score, String qualified, int ecn) {
        this.yes = yes;
        this.no = no;
        this.score = score;
        this.qualified=qualified;
        this.ecn=ecn;
    }

    private double score;//结果
    private String password;//密码

    //空参构造
    public Student(){

    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "ecn=" + ecn +
                ", yes=" + yes +
                ", no=" + no +
                ", score=" + score +
                ", password='" + password + '\'' +
                '}';
    }

    public int getEcn() {
        return ecn;
    }

    public void setEcn(int ecn) {
        this.ecn = ecn;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
