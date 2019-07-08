package com.cheer.exam.service;


import com.cheer.exam.model.Admin;

public interface AdminService {
    Admin getAdmin(String username) throws Exception;

    boolean checkLogin(String username, String password);
}
