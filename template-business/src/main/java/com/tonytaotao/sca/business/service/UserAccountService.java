package com.tonytaotao.sca.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tonytaotao.sca.business.entity.UserAccount;

/**
 * <p>
 *  服务接口类
 * </p>
 *
 * @author tonytaotao
 * @since 2019-10-22
 */
public interface UserAccountService extends IService<UserAccount> {

    void saveOrUpdateUserAccount(UserAccount entity);

    Boolean deleteUserAccountById(Long id);

}
