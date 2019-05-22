package com.tryndamere.zhibo8.harvest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tryndamere.zhibo8.harvest.entity.Comment;
import com.tryndamere.zhibo8.harvest.entity.User;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dave
 * @since 2019-05-21
 */
public interface IUserService extends IService<User> {

    /**
     * 保存用户信息
     *
     * @param e
     */
    void saveUserInfo(Comment e);

    /**
     * 根据mUid查询用户
     *
     * @param mUid
     * @return
     */
    User queryUserByMUid(String mUid);
}
