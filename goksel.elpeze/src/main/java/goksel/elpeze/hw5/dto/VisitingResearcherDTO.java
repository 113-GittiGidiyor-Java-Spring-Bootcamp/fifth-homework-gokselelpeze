package goksel.elpeze.hw5.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = VisitingResearcherDTO.class)
public class VisitingResearcherDTO extends InstructorDTO {

    @ApiModelProperty(example = "100")
    @NotNull
    private double hourlySalary;

}
