package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tryndamere.zhibo8.harvest.entity.Comment;
import com.tryndamere.zhibo8.harvest.mapper.CommentMapper;
import com.tryndamere.zhibo8.harvest.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;
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

    @Override
    public void saveComment(List<GatherCommentVo> list, Long newsId) {
        list.forEach(e -> {
            //保存父节点
            final Comment comment = setComment(e, newsId);
            Long commentId = IdWorker.getId();
            comment.setId(commentId);
            this.save(comment);

            //保存子节点
            if (!e.getChildren().isEmpty()) {
                e.getChildren().forEach(child -> {
                    Comment childComment = setComment(child, newsId);
                    childComment.setParentId(commentId);
                    this.save(childComment);
                });
            }
        });
    }

    private Comment setComment(GatherCommentVo vo, Long newsId) {
        return new Comment()
                .setNewsId(newsId)
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
}
