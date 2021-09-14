package goksel.elpeze.hw5.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = VisitingResearcherDTO.class,
                name = "VisitingResearcher"),
        @JsonSubTypes.Type(value = PermanentInstructorDTO.class,
                name = "PermanentInstructor")
})
public class InstructorDTO {

    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(example = "Koray Güney")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(example = "İstanbul")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @ApiModelProperty(example = "5415174483")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

}