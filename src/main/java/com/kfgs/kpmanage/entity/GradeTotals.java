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
* 各部分每个考官打的总分计算
*
* @author lsy
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("GRADE_TOTALS")
    @ApiModel(value="GradeTotals对象", description="各部分每个考官打的总分计算")
    public class GradeTotals extends Model<GradeTotals> {

private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "考生")
        @TableField("CANDIDATE")
    private String candidate;

            @ApiModelProperty(value = "固定部分得分")
        @TableField("REGULAR")
    private String regular;

            @ApiModelProperty(value = "个人部分得分")
        @TableField("PERSONAL")
    private String personal;

            @ApiModelProperty(value = "个人总得分")
        @TableField("TOTAL")
    private String total;

            @ApiModelProperty(value = "考核类型")
        @TableField("TYPE")
    private String type;

            @ApiModelProperty(value = "考官")
        @TableField("EXAMINER")
    private String examiner;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
