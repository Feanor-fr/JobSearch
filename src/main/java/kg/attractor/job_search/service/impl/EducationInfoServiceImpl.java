package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.EducationInfoDao;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.exceptions.EducationInfoNotFoundException;
import kg.attractor.job_search.model.EducationInfo;
import kg.attractor.job_search.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;

    @Override
    public void create(EducationInfoDto educationInfoDto, Integer resumeId) {
        EducationInfo educationInfo = convertToEducationInfo(educationInfoDto);
        educationInfoDao.save(educationInfo, resumeId);
    }

    @Override
    public void update(Integer id, EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = educationInfoDao.findById(id);

        if (educationInfo == null) {
            throw new EducationInfoNotFoundException();
        }

        EducationInfo check = convertToEducationInfo(educationInfoDto);
        educationInfoDao.update(id, correctEducationInfoForDao(educationInfo, check));
    }

    @Override
    public void delete(Integer id) {
        EducationInfo educationInfo = educationInfoDao.findById(id);

        if (educationInfo == null) {
            throw new EducationInfoNotFoundException();
        }

        educationInfoDao.deleteById(id);
    }

    @Override
    public void deleteAllByResumeId(Integer resumeId) {
        educationInfoDao.deleteAllByResumeId(resumeId);
    }

    @Override
    public List<EducationInfoDto> findAllByResumeId(Integer resumeId) {
        return educationInfoDao.findByResumeId(resumeId).stream().map(this::convertToEducationInfoDto).toList();
    }

    private EducationInfo convertToEducationInfo(EducationInfoDto educationInfoDto) {
        return EducationInfo.builder()
                .resumeId(educationInfoDto.getResumeId())
                .institution(educationInfoDto.getInstitution())
                .program(educationInfoDto.getProgram())
                .startDate(educationInfoDto.getStartDate())
                .endDate(educationInfoDto.getEndDate())
                .degree(educationInfoDto.getDegree())
                .build();
    }

    private EducationInfoDto convertToEducationInfoDto(EducationInfo educationInfo) {
        return EducationInfoDto.builder()
                .resumeId(educationInfo.getResumeId())
                .institution(educationInfo.getInstitution())
                .program(educationInfo.getProgram())
                .startDate(educationInfo.getStartDate())
                .endDate(educationInfo.getEndDate())
                .degree(educationInfo.getDegree())
                .build();
    }

    private EducationInfo correctEducationInfoForDao(EducationInfo educationInfo, EducationInfo check) {
        if(check == null){
            return educationInfo;
        }

        return EducationInfo.builder()
                .id(educationInfo.getId())
                .resumeId(educationInfo.getResumeId())
                .institution(Optional.ofNullable(check.getInstitution()).orElse(educationInfo.getInstitution()))
                .program(Optional.ofNullable(check.getProgram()).orElse(educationInfo.getProgram()))
                .startDate(Optional.ofNullable(check.getStartDate()).orElse(educationInfo.getStartDate()))
                .endDate(Optional.ofNullable(check.getEndDate()).orElse(educationInfo.getEndDate()))
                .degree(Optional.ofNullable(check.getDegree()).orElse(educationInfo.getDegree()))
                .build();
    }

}
