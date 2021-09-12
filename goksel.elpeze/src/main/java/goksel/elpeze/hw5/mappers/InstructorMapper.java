package goksel.elpeze.hw5.mappers;

import goksel.elpeze.hw5.dto.PermanentInstructorDTO;
import goksel.elpeze.hw5.dto.VisitingResearcherDTO;
import goksel.elpeze.hw5.model.PermanentInstructor;
import goksel.elpeze.hw5.model.VisitingResearcher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InstructorMapper {

    public abstract PermanentInstructor mapFromPermanentInstructorDTOtoPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);

    public abstract PermanentInstructorDTO mapFromPermanentInstructortoPermanentInstructorDTO(PermanentInstructor permanentInstructor);

    public abstract VisitingResearcher mapFromVisitingResearcherDTOtoVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO);

    public abstract VisitingResearcherDTO mapFromVisitingResearchertoVisitingResearcherDTO(VisitingResearcher visitingResearcher);

}
