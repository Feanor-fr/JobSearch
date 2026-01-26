package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.dto.update.WorkExperienceInfoForUpdateDto;
import kg.attractor.job_search.exceptions.WorkExperienceInfoNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.WorkExperienceInfo;
import kg.attractor.job_search.repository.WorkExperienceInfoRepository;
import kg.attractor.job_search.service.WorkExperienceInfoService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {
   private final WorkExperienceInfoRepository repository;

    @Override
    public void create(WorkExperienceInfoDto workExperienceInfoDto, Resume resume){
        WorkExperienceInfo workExperienceInfo = ConvertHelper.convertToWorkExperienceInfo(workExperienceInfoDto, resume);
      repository.save(workExperienceInfo);
    }

    @Override
    public void update(Integer id, WorkExperienceInfoForUpdateDto workExperienceInfoDto){
        WorkExperienceInfo workExperienceInfo = repository
                .findById(id)
                .orElseThrow(WorkExperienceInfoNotFoundException::new);

        repository.save(
                ConvertHelper.correctWorkExperienceInfoForDao
                        (workExperienceInfo, workExperienceInfoDto));
    }

    @Override
    public void delete(Integer id){
        WorkExperienceInfo workExperienceInfo = repository
                .findById(id)
                .orElseThrow(WorkExperienceInfoNotFoundException::new);

        repository.delete(workExperienceInfo);
    }

    @Override
    public List<WorkExperienceInfoDto> findAllByResumeId(Integer resumeId){

        return repository.findWorkExperienceInfosByResume_Id(resumeId)
                .stream()
                .map(ConvertHelper::convertToWorkExperienceInfoDto)
                .toList();
    }

    @Override
    public boolean isWorkExpBelongResume(Integer resumeId, Integer workExpId){
        if (resumeId == null || workExpId == null) {
            return false;
        }
        return repository.findResumeIdByWorkId(workExpId)
                .map(foundResumeId -> foundResumeId.equals(resumeId))
                .orElse(false);
    }

}
