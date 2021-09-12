package goksel.elpeze.hw5.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends BaseEntity{
//public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String courseName;

    @NotBlank
    private String courseCode;

    @Min(value = 1, message = "The credit score cannot be less than 1")
    @Max(value = 4, message = "The credit score cannot be greater than 4")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int creditScore;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_instructor")
    private Instructor instructor;


}
