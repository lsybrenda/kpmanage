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
    @TableName("GRADE_SCORES")
    @ApiModel(value="GradeScores对象", description="")
    public class GradeScores extends Model<GradeScores> {

    private static final long serialVersionUID = 1L;

    @TableField("EXA_ID")
    private String exaId;

    @TableField("EXAMINER")
    private String examiner;

    @TableField("SCORE")
    private String score;

    @TableField("CANDIDATE")
    private String candidate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
