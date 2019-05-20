package com.tryndamere.zhibo8.harvest.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dave
 * @since 2019-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    private Long newsId;

    private String fileName;

    @TableField("mUid")
    private String mUid;

    private String userName;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer up;

    private Integer down;

    private String deviceName;

    private Integer replyCount;

    private Long parentId;


}
