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
 * 事件操作内容记录表
 * </p>
 *
 * @author 
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_event_content")
public class EventContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事件内容记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事件id
     */
    private Long eventId;

    /**
     * 操作数据唯一标识id
     */
    private Long dataId;

    /**
     * 操作数据标识名称
     */
    private String dataName;

    /**
     * 修改字段英文名
     */
    private String keyEn;

    /**
     * 修改字段中文名
     */
    private String keyZh;

    /**
     * 修改前的值
     */
    private String olValue;

    /**
     * 修改后的值
     */
    private String neValue;


}
