package com.tryndamere.zhibo8.harvest.service;

import com.tryndamere.zhibo8.harvest.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dave
 * @since 2019-05-16
 */
public interface ICommentService extends IService<Comment> {

    void saveComment(List<GatherCommentVo> list,Long newsId);
}
