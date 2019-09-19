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
 * 事件发生地记录表
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_event_place")
public class EventPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事件发生地记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 链路标识
     */
    private String spanId;

    /**
     * 操作页面代码
     */
    private String placePageCode;

    /**
     * 操作页面名称
     */
    private String placePageName;

    /**
     * 操作平台代码
     */
    private String placePlatformCode;

    /**
     * 操作平台名称
     */
    private String placePlatformName;

    /**
     * 操作资源URL
     */
    private String placeUrl;


}
