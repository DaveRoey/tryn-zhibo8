package com.tryndamere.zhibo8.harvest.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="News对象", description="")
public class News implements Serializable {
    @TableId(type = IdType.AUTO)
    private long id;
    private static final long serialVersionUID = 1L;

    private Long reporterId;

    private String url;

    private String title;

    private LocalDateTime createTime;

    private String content;

    private String origin;


}
