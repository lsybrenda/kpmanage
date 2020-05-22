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
    @TableName("GRADE_RELATION")
    @ApiModel(value="GradeRelation对象", description="")
    public class GradeRelation extends Model<GradeRelation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考官")
    @TableField("EXAMINER")
    private String examiner;

    @ApiModelProperty(value = "考生")
    @TableField("CANDIDATE")
    private String candidate;

    @ApiModelProperty(value = "1.上级  2.同级  3.服务对象")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "分组情况")
    @TableField("GROUPS")
    private String groups;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
