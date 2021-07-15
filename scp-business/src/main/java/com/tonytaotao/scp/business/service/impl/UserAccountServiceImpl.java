package com.tonytaotao.scp.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tonytaotao.scp.business.entity.UserAccount;
import com.tonytaotao.scp.business.mapper.UserAccountMapper;
import com.tonytaotao.scp.business.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户账户服务接口实现
 * @author tonytaotao
 */
@Service
@Slf4j
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
