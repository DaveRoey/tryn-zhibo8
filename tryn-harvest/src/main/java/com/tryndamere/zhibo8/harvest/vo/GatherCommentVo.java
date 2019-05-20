package com.tryndamere.zhibo8.harvest.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @Author Dave
 * @Date 2019/5/11
 * @Description
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GatherCommentVo {
    private String id;
    @JsonProperty("filename")
    private String fileName;
    @JsonProperty("m_uid")
    private String mUid;
    private String username;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("createtime")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("updatetime")
    private Date updateTime;
    private Integer up;
    private Integer down;
    private String device;
    @JsonProperty("reply_count")
    private Integer replyCount;
    List<GatherCommentVo> children;


}
