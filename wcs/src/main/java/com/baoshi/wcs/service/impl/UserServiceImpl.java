package com.baoshi.wcs.service.impl;

import com.baoshi.wcs.entity.User;
import com.baoshi.wcs.dao.UserMapper;
import com.baoshi.wcs.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
