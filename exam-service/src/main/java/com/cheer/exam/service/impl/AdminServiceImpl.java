package com.cheer.exam.service.impl;

import com.cheer.exam.dao.AdminMapper;
import com.cheer.exam.model.Admin;
import com.cheer.exam.service.AdminService;
import com.cheer.exam.utils.MybatisUtils;
import com.cheer.exam.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*admin dao实现类*/
@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin getAdmin(String username) throws Exception {
        Admin admin = adminMapper.getAdmin ( username );
        return admin;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Admin admin = this.adminMapper.getAdmin ( username );
        if (admin == null) {
            throw new RuntimeException ( "账号密码输入有误" );
        }

        //如果找到了比较该账号的密码是否相同
        if(StringUtils.encrypt ( password ).equals ( admin.getPassword () )){
            return true;
        }else {
            return false;
        }

    }

    /* //通过username找到admin
    public Admin getAdmin(String username) throws Exception {
        SqlSession session = MybatisUtils.getSqlSession ();
        Admin admin = session.getMapper ( AdminMapper.class ).getAdmin ( username );
        MybatisUtils.closeSqlSession ( session );
        if (admin == null) {
            throw new Exception ( "数据库中没有这个管理者" );
        }
        return admin;
    }

    //通过username找到admin对象 判断密码是否相等
    public boolean checkLogin(String username,String password){
       SqlSession session = MybatisUtils.getSqlSession ();
      Admin admin = session.getMapper ( AdminMapper.class ).getAdmin ( username );
        MybatisUtils.closeSqlSession ( session );
        if(admin == null){
            return false;
        }

        //如果找到了比较该账号的密码是否相同
        if(StringUtils.encrypt ( password ).equals ( admin.getPassword () )){
            return true;
        }else {
            return false;
        }*/
    }




