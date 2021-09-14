package goksel.elpeze.hw5.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(example = "Data Structrues")
    @NotBlank(message = "Course name is mandatory.")
    private String courseName;

    @ApiModelProperty(example = "CME3204")
    @NotBlank(message = "Course code is mandatory.")
    private String courseCode;

    @ApiModelProperty(example = "4")
    @NotNull(message = "Credit score is mandatory.")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double creditScore;

    @ApiModelProperty(example = "[1, 24234, 3, 453463]")
    private List<Integer> studentIds;

    @ApiModelProperty(example = "1")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int instructorId;


}
