package com.rao.study.trace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事件用户体系表
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_event_user")
public class EventUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事件用户记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事件id
     */
    private Long eventId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String userMobile;

    /**
     * 用户来源
     */
    private String userOrigin;


}
