package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.EducationInfoDao;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.exceptions.EducationInfoNotFoundException;
import kg.attractor.job_search.exceptions.InvalidDateIntervalException;
import kg.attractor.job_search.model.EducationInfo;
import kg.attractor.job_search.service.EducationInfoService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;

    @Override
    public void create(EducationInfoDto educationInfoDto, Integer resumeId) {

        if (educationInfoDto.getEndDate().isBefore(educationInfoDto.getStartDate())) {
            throw new InvalidDateIntervalException("Не верный временной промежуток между датами");
        }

        EducationInfo educationInfo = ConvertHelper.convertToEducationInfo(educationInfoDto);
        educationInfoDao.save(educationInfo, resumeId);
    }

    @Override
    public void update(Integer id, EducationInfoDto educationInfoDto) {
        EducationInfo educationInfo = educationInfoDao.findById(id)
                .orElseThrow(EducationInfoNotFoundException::new);

        if (educationInfoDto.getEndDate().isBefore(educationInfoDto.getStartDate())) {
            throw new InvalidDateIntervalException("Не верный временной промежуток между датами");
        }

        EducationInfo check = ConvertHelper.convertToEducationInfo(educationInfoDto);
        educationInfoDao.update(id, ConvertHelper.correctEducationInfoForDao(educationInfo, check));
    }

    @Override
    public void delete(Integer id) {
        EducationInfo educationInfo = educationInfoDao.findById(id)
                .orElseThrow(EducationInfoNotFoundException::new);
        educationInfoDao.deleteById(id);
    }

    @Override
    public List<EducationInfoDto> findAllByResumeId(Integer resumeId) {
        return educationInfoDao.findByResumeId(resumeId)
                .orElse(List.of())
                .stream()
                .map(ConvertHelper::convertToEducationInfoDto)
                .toList();
    }

    @Override
    public boolean isEducationBelongResume(Integer resumeId, Integer eduId){
        if (resumeId == null || eduId == null) {
            return false;
        }
        return educationInfoDao.findResumeIdByEducationId(eduId)
                .map(FoundResumeId -> FoundResumeId.equals(resumeId))
                .orElse(false);
    }

}
