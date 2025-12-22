package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;

public interface WorkExperienceInfoService {
    void create(WorkExperienceInfoDto workExperienceInfoDto, Integer resumeId);

    void update(Integer id, WorkExperienceInfoDto workExperienceInfoDto);

    void delete(Integer id);

    void deleteAllByResumeId(Integer resumeId);
}
