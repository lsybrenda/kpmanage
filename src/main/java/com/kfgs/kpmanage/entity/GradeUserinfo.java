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

            @ApiModelProperty(value = "K0001")
        @TableField("ID")
    private String id;

            @ApiModelProperty(value = "张三、李四")
        @TableField("NAME")
    private String name;

            @ApiModelProperty(value = "zhangsan@cnipa.gov.cn")
        @TableField("EMAIL")
    private String email;

            @ApiModelProperty(value = "总经理
,部副主任
")
        @TableField("POSITION")
    private String position;

            @ApiModelProperty(value = "综合管理部、IT事业部")
        @TableField("DEPARTMENT")
    private String department;

            @ApiModelProperty(value = "是否参与打分")
        @TableField("ISDAFEN")
    private String isdafen;

            @ApiModelProperty(value = "入职时间")
        @TableField("HIREDATE")
    private String hiredate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
