package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.common.RedisNameSpace;
import com.tryndamere.zhibo8.harvest.entity.User;
import com.tryndamere.zhibo8.harvest.mapper.UserMapper;
import com.tryndamere.zhibo8.harvest.service.IUserService;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;
import com.tryndamere.zhibo8.redis.repostory.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dave
 * @since 2019-05-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RedisManager redisManager;

    @Override
    public void saveUserInfo(GatherCommentVo e) {
        User user = queryUserByMUid(e.getMUid());
        if (user == null) {
            //保存用户信息
            user = new User()
                    .setId(IdWorker.getId())
                    .setMUid(e.getMUid())
                    .setDeviceName(e.getDevice())
                    .setUserName(e.getUsername());
            this.save(user);
            //放入缓存
            String key = RedisNameSpace.USER_NAME_SPACE + user.getMUid();
            redisManager.set(key, user, 300L);
        }


    }

    @Override
    public User queryUserByMUid(String mUid) {
        String key = RedisNameSpace.USER_NAME_SPACE + mUid;
        User user = (User) redisManager.get(key);

        if (user == null) {
            user = this.getOne(new LambdaQueryWrapper<User>()
                    .eq(User::getMUid, mUid));
        }
        return user;
    }
}
