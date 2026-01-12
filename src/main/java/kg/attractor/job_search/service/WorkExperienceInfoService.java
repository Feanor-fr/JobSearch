package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.dto.update.WorkExperienceInfoForUpdateDto;

import java.util.List;

public interface WorkExperienceInfoService {
    void create(WorkExperienceInfoDto workExperienceInfoDto, Integer resumeId);

    void update(Integer id, WorkExperienceInfoForUpdateDto workExperienceInfoDto);

    void delete(Integer id);

    List<WorkExperienceInfoDto> findAllByResumeId(Integer resumeId);

    boolean isWorkExpBelongResume(Integer resumeId, Integer workExpId);
}
