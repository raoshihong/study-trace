package com.rao.study.trace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事件记录总表
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事件记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事件名称
     */
    private String name;

    /**
     * 事件发生时间
     */
    private LocalDateTime time;

    /**
     * 事件发生方式
     */
    private String type;


}
