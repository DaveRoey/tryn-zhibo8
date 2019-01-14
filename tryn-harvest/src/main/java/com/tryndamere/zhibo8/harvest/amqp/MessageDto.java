package com.tryndamere.zhibo8.harvest.amqp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.ConstructorArgs;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dave on 2019/1/7
 * Describes
 */
@Data
@AllArgsConstructor
public class MessageDto implements Serializable {
    String mac;
    String status;
    Date createTime;

}
