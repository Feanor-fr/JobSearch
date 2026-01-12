package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.WorkExperienceInfoDao;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.dto.update.WorkExperienceInfoForUpdateDto;
import kg.attractor.job_search.exceptions.WorkExperienceInfoNotFoundException;
import kg.attractor.job_search.model.WorkExperienceInfo;
import kg.attractor.job_search.service.WorkExperienceInfoService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public void create(WorkExperienceInfoDto workExperienceInfoDto, Integer resumeId){
        WorkExperienceInfo workExperienceInfo = ConvertHelper.convertToWorkExperienceInfo(workExperienceInfoDto);
        workExperienceInfoDao.save(workExperienceInfo, resumeId);
    }

    @Override
    public void update(Integer id, WorkExperienceInfoForUpdateDto workExperienceInfoDto){
        WorkExperienceInfo workExperienceInfo = workExperienceInfoDao
                .findById(id)
                .orElseThrow(WorkExperienceInfoNotFoundException::new);

        workExperienceInfoDao.update(id, ConvertHelper.correctWorkExperienceInfoForDao(workExperienceInfo, workExperienceInfoDto));
    }

    @Override
    public void delete(Integer id){
        WorkExperienceInfo workExperienceInfo = workExperienceInfoDao
                .findById(id)
                .orElseThrow(WorkExperienceInfoNotFoundException::new);

        workExperienceInfoDao.deleteById(id);
    }

    @Override
    public List<WorkExperienceInfoDto> findAllByResumeId(Integer resumeId){
        return workExperienceInfoDao.findByResumeId(resumeId).stream().map(ConvertHelper::convertToWorkExperienceInfoDto).toList();
    }

    @Override
    public boolean isWorkExpBelongResume(Integer resumeId, Integer workExpId){
        if (resumeId == null || workExpId == null) {
            return false;
        }
        return workExperienceInfoDao.findResumeIdByWorkExpId(workExpId)
                .map(FoundResumeId -> FoundResumeId.equals(resumeId))
                .orElse(false);
    }

}
