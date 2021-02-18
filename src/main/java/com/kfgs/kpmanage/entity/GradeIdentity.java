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
* 身份证号与编号对应表
*
* @author lsy
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("GRADE_IDENTITY")
    @ApiModel(value="GradeIdentity对象", description="身份证号与编号对应表")
    public class GradeIdentity extends Model<GradeIdentity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "身份证号")
    @TableField("IDNUMBER")
    private String idnumber;

    @ApiModelProperty(value = "打分编号")
    @TableField("USERID")
    private String userid;

    @ApiModelProperty(value = "登录密码")
    @TableField("PASSWORD")
    private String password;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
