package goksel.elpeze.hw5.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructorDTO {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(example = "Gökhan Göksel Elpeze")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(example = "01.01.1996")
    @NotNull(message = "Birthdate is mandatory")
    @DateTimeFormat
    private LocalDate birthdate;

    @ApiModelProperty(example = "Istanbul")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @ApiModelProperty(example = "M")
    @NotBlank(message = "Gender is mandatory")
    private char gender;

}
