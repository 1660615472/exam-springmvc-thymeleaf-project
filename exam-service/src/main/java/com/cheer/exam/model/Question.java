package com.cheer.exam.model;

import java.io.Serializable;

//题库 javabean
public class Question implements Serializable {
    private int no;
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;

    public void setNo(int no){
        this.no = no;
    }
    public int getNo(){
        return no;
    }

    public Question() {
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }



    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public String toString() {
        return "Question{" +
                "no=" + no +
                ", question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public Question(int no , String question, String a, String b, String c, String d, String answer) {
        this.no =no;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }





}
