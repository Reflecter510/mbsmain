package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 前端用户 权限表实体类
 *
 * @author Reflecter510
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "Permission")
@Data
public class FrontUserPermissionDO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 有效验证类型
     */
    private Integer type;

    /**
     * 有效期
     */
    private Date validityTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 扩展字段
     */
    private Long extra;

    /**
     * 有效次数
     */
    private Integer validityCount;
}