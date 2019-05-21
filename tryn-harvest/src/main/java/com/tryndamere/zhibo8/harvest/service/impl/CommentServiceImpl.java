package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tryndamere.zhibo8.harvest.entity.Comment;
import com.tryndamere.zhibo8.harvest.entity.User;
import com.tryndamere.zhibo8.harvest.mapper.CommentMapper;
import com.tryndamere.zhibo8.harvest.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.service.IUserService;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dave
 * @since 2019-05-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private IUserService userService;

    @Override
    public void saveComment(List<GatherCommentVo> list, Long newsId) {
        list.forEach(e -> {
            //保存父节点
            final Comment comment = setComment(e, newsId);
            Long commentId = IdWorker.getId();
            comment.setId(commentId);
            this.doSave(comment);
            userService.saveUserInfo(e);

            //保存子节点
            if (!e.getChildren().isEmpty()) {
                e.getChildren().forEach(child -> {
                    Comment childComment = setComment(child, newsId);
                    childComment.setParentId(commentId);
                    this.doSave(childComment);
                    userService.saveUserInfo(child);
                });
            }
        });
    }

    private Comment setComment(GatherCommentVo vo, Long newsId) {
        return new Comment()
                .setNewsId(newsId)
                .setCommentId(vo.getId())
                .setContent(vo.getContent())
                .setCreateTime(vo.getCreateTime())
                .setUpdateTime(vo.getUpdateTime())
                .setUp(vo.getUp())
                .setDown(vo.getDown())
                .setDeviceName(vo.getDevice())
                .setFileName(vo.getFileName())
                .setMUid(vo.getMUid())
                .setUserName(vo.getUsername())
                .setReplyCount(vo.getReplyCount());
    }


    @Override
    public Comment queryCommentByCommentIdAndNewsId(Long newsId, String commentId) {
        return this.getOne(new QueryWrapper<Comment>()
                .lambda()
                .eq(Comment::getNewsId, newsId)
                .eq(Comment::getCommentId, commentId));
    }

    private void doSave(Comment param) {
        Comment comment = this.queryCommentByCommentIdAndNewsId(param.getNewsId(), param.getCommentId());

        if (null == comment) {
            //保存
            this.save(param);
        } else {
            //更新
            this.update(new Comment(), new UpdateWrapper<Comment>()
                    .lambda()
                    .set(Comment::getUp, param.getUp())
                    .set(Comment::getDown, param.getDown())
                    .set(Comment::getUpdateTime, param.getUpdateTime())
                    .eq(Comment::getId, comment.getId()));
        }
    }

}
