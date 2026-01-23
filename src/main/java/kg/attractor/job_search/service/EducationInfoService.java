package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.dto.update.EducationInfoForUpdateDto;
import kg.attractor.job_search.model.Resume;

import java.util.List;

public interface EducationInfoService {
    void create(EducationInfoDto educationInfoDto, Resume resume);

    void update(Integer id, EducationInfoForUpdateDto educationInfoDto);

    void delete(Integer id);

    List<EducationInfoDto> findAllByResumeId(Integer resumeId);

    boolean isEducationBelongResume(Integer resumeId, Integer eduId);
}
