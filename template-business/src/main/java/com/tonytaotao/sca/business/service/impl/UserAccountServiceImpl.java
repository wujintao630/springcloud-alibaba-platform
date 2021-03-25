package com.tonytaotao.sca.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tonytaotao.sca.business.entity.UserAccount;
import com.tonytaotao.sca.business.mapper.UserAccountMapper;
import com.tonytaotao.sca.business.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务接口实现类
 * </p>
 *
 * @author tonytaotao
 * @since 2019-10-22
 */
@Service
@Slf4j
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Override
    public void saveOrUpdateUserAccount(UserAccount entity) {
        this.saveOrUpdate(entity);
    }

    @Override
    public Boolean deleteUserAccountById(Long id) {
        return this.removeById(id);
    }

}
