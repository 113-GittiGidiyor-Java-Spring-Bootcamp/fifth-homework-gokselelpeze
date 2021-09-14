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
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "Gökhan Göksel Elpeze")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(example = "1996-04-24")
    @NotNull(message = "Birthdate is mandatory")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate birthdate;

    @ApiModelProperty(example = "Istanbul")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @ApiModelProperty(example = "M")
    @NotBlank(message = "Gender is mandatory")
    private char gender;

}
