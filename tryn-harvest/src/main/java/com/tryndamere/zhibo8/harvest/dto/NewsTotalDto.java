package com.tryndamere.zhibo8.harvest.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author Dave
 * @Date 2019/5/16
 * @Description
 */
@Data
@Accessors(chain = true)
public class NewsTotalDto implements Serializable {
    private Long newsId;
    private String url;
    private Integer pageTotal;
    private Date createDate;
    private String fileName;
}
