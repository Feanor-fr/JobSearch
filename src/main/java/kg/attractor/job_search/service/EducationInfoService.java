package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.EducationInfoDto;

import java.util.List;

public interface EducationInfoService {
    void create(EducationInfoDto educationInfoDto, Integer resumeId);

    void update(Integer id, EducationInfoDto educationInfoDto);

    void delete(Integer id);

    List<EducationInfoDto> findAllByResumeId(Integer resumeId);

    boolean isEducationBelongResume(Integer resumeId, Integer eduId);
}
