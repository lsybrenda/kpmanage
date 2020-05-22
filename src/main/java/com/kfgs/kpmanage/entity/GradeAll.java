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
* 每个人总得分表
*
* @author lsy
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("GRADE_ALL")
    @ApiModel(value="GradeAll对象", description="每个人总得分表")
    public class GradeAll extends Model<GradeAll> {

private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableField("ID")
    private String id;

    @ApiModelProperty(value = "按照职位加权后的总分")
    @TableField("SCORE")
    private String score;

    @ApiModelProperty(value = "上级平均分")
    @TableField("HIGHER")
    private String higher;

    @ApiModelProperty(value = "同级平均分")
    @TableField("SAME")
    private String same;

    @ApiModelProperty(value = "下级平均分")
    @TableField("LOWER")
    private String lower;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
