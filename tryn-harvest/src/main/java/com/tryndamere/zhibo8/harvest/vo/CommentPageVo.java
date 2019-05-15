package com.tryndamere.zhibo8.harvest.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Dave
 * @Date 2019/5/11
 * @Description 新闻分页帮助
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentPageVo {
    @JsonProperty("root_num")
    private Integer rootNum;
    @JsonProperty("root_normal_num")
    private Integer rootNormalNum;
    @JsonProperty("all_num")
    private Integer allNum;
    @JsonProperty("all_short_num")
    private Integer allShortNum;
    @JsonProperty("hot_num")
    private Integer hotNum;
    @JsonProperty("time_interval")
    private Integer timeInterval;
    private Integer pageTotal;


    public Integer getPageTotal() {
        return (int) (Math.ceil(this.getRootNum() / 100.0) - 1);
    }

}
