package com.cheer.exam.web.controller;
import com.cheer.exam.model.Question;
import com.cheer.exam.model.Student;
import com.cheer.exam.service.QuestionService;
import com.cheer.exam.service.StudentInfoService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller//放入spring容器 由spring管理
public class StudentController {
    @Autowired
    QuestionService questionService;
    @Autowired
    StudentInfoService studentInfoService;

    @GetMapping("studentLogin")
    public String studentLogin(){
        return "studentLogin";
    }

    @PostMapping("studentLogin")
    public String studentLgoin(Student student, HttpSession session){
        log.debug ( student+">>>>>>>>>>>>>>>>>>>>>");
        //判断数据库有没有这个学生信息
        if(this.studentInfoService.checkStudentLogin (student.getEcn (),student.getPassword ())){
            log.debug ( "登陆成功!" );
          Integer ecn =  student.getEcn ();
            //把考生信息放到model域里
            session.setAttribute ( "student",student );
            session.setAttribute ( "ecn",ecn );
            //跳转到考试页面
            return "examinationPage";
        }else {//学号密码不对就返回学生登陆界面
            return "redirect:/studentLogin";
        }
    }

    @ResponseBody
    @GetMapping("answerResult")
    public Double answerResult(@Param ( "answer" ) String[] answer, HttpSession session, Model model){
        Integer ecn = (Integer) session.getAttribute ( "ecn" );
        log.debug ( ecn+">>>>>>>>>>>>>>>>>>>>>" );
        //获取所有正确答案
     List<String> answerList = this.questionService.getKey ();
        int t=0;//答对数量
        int f=0;//打错数量
        double score=0;//总分
        String qualified =null;//是否合格
        for(int i =0 ;i<answer.length;i++){
            if(answer[i].equals ( answerList.get ( i ).split("：")[1]) ){
                t++;
                score+=16.6;//答对一次总分+16.6
            }else {
                f++;
            }
        }
        if(score>=66){
            qualified="合格";
        }else {
            qualified="不合格";
        }

        Student studentInfo = new Student (t,f,score,qualified,ecn);

        int update = this.studentInfoService.update ( studentInfo );
       /* writer.println(update);
        writer.close();*/
       return score;
    }



    @RequestMapping("result")
    public String result(Model model ,HttpSession session){
        Integer ecn =(Integer) session.getAttribute ( "ecn" );
        log.debug ( ecn+">>>>>>>>>>>>>>.." );
      Student student = studentInfoService.getStudentInfo ( ecn );
      log.debug ( student+">>>>>>>>>>>>>>>" );
      model.addAttribute ( "student",student );
      return "result";
    }

}
