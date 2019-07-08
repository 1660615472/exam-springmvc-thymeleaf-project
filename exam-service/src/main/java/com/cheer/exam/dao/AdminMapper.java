package com.cheer.exam.dao;


import com.cheer.exam.model.Admin;

/*管理员dao接口*/
public interface AdminMapper {
 Admin getAdmin(String username);
}
