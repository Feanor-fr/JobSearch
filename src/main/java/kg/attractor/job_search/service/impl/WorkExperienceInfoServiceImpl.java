package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.WorkExperienceInfoDao;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.exceptions.WorkExperienceInfoNotFoundException;
import kg.attractor.job_search.model.WorkExperienceInfo;
import kg.attractor.job_search.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public void create(WorkExperienceInfoDto workExperienceInfoDto, Integer resumeId){
        WorkExperienceInfo workExperienceInfo = convertToWorkExperienceInfo(workExperienceInfoDto);
        workExperienceInfoDao.save(workExperienceInfo, resumeId);
    }

    @Override
    public void update(Integer id, WorkExperienceInfoDto workExperienceInfoDto){
        WorkExperienceInfo workExperienceInfo = workExperienceInfoDao.findById(id);

        if(workExperienceInfo == null){
            throw new WorkExperienceInfoNotFoundException();
        }

        WorkExperienceInfo check = convertToWorkExperienceInfo(workExperienceInfoDto);
        workExperienceInfoDao.update(id, correctWorkExperienceInfoForDao(workExperienceInfo, check));
    }

    @Override
    public void delete(Integer id){
        WorkExperienceInfo workExperienceInfo = workExperienceInfoDao.findById(id);

        if(workExperienceInfo == null){
            throw new WorkExperienceInfoNotFoundException();
        }

        workExperienceInfoDao.deleteById(id);
    }

    @Override
    public void deleteAllByResumeId(Integer resumeId){
        workExperienceInfoDao.deleteAllByResumeId(resumeId);
    }

    public List<WorkExperienceInfoDto> findAllByResumeId(Integer resumeId){
        return workExperienceInfoDao.findByResumeId(resumeId).stream().map(this::convertToWorkExperienceInfoDto).toList();
    }

    private WorkExperienceInfo convertToWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto){
        return WorkExperienceInfo.builder()
                .resumeId(workExperienceInfoDto.getResumeId())
                .years(workExperienceInfoDto.getYears())
                .companyName(workExperienceInfoDto.getCompanyName())
                .position(workExperienceInfoDto.getPosition())
                .responsibilities(workExperienceInfoDto.getResponsibilities())
                .build();
    }

    private WorkExperienceInfoDto convertToWorkExperienceInfoDto(WorkExperienceInfo workExperienceInfo){
        return WorkExperienceInfoDto.builder()
                .resumeId(workExperienceInfo.getResumeId())
                .years(workExperienceInfo.getYears())
                .companyName(workExperienceInfo.getCompanyName())
                .position(workExperienceInfo.getPosition())
                .responsibilities(workExperienceInfo.getResponsibilities())
                .build();
    }

    private WorkExperienceInfo correctWorkExperienceInfoForDao(WorkExperienceInfo workExperienceInfo, WorkExperienceInfo check){
        return WorkExperienceInfo.builder()
                .id(workExperienceInfo.getId())
                .resumeId(workExperienceInfo.getResumeId())
                .years(Optional.ofNullable(check.getYears()).orElse(workExperienceInfo.getYears()))
                .companyName(Optional.ofNullable(check.getCompanyName()).orElse(workExperienceInfo.getCompanyName()))
                .position(Optional.ofNullable(check.getPosition()).orElse(workExperienceInfo.getPosition()))
                .responsibilities(Optional.ofNullable(check.getResponsibilities()).orElse(workExperienceInfo.getResponsibilities()))
                .build();
    }
}
