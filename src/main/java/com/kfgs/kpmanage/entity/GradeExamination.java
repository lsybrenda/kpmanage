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
    @TableName("GRADE_EXAMINATION")
    @ApiModel(value="GradeExamination对象", description="")
    public class GradeExamination extends Model<GradeExamination> {

private static final long serialVersionUID = 1L;

        @TableField("EXA_ID")
    private String exaId;

        @TableField("EXAMINATIONQUESTIONS")
    private String examinationquestions;

        @TableField("OWNER")
    private String owner;

        @TableField("SAMEWORK")
    private String samework;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
