package com.kfgs.kpmanage.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 
*
* @author lsy
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("GRADE_USERINFO")
    @ApiModel(value="GradeUserinfo对象", description="")
    public class GradeUserinfo extends Model<GradeUserinfo> {

private static final long serialVersionUID = 1L;

    @TableField("ID")
    private String id;

    @TableField("NAME")
    private String name;

    @TableField("EMAIL")
    private String email;

    @TableField("POSITION")
    private String position;

    @TableField("DEPARTMENT")
    private String department;

    @TableField("ISDAFEN")
    private String isdafen;

    @TableField("HIREDATE")
    private String hiredate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
