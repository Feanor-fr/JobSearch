package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ContactInfoDao;
import kg.attractor.job_search.dto.ContactInfoDto;
import kg.attractor.job_search.dto.update.ContactInfoForUpdateDto;
import kg.attractor.job_search.exceptions.ContactInfoNotFoundException;
import kg.attractor.job_search.model.ContactInfo;
import kg.attractor.job_search.service.ContactInfoService;
import kg.attractor.job_search.service.ContactTypeService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService{
    private final ContactInfoDao contactInfoDao;
    private final ContactTypeService contactTypeService;

    @Override
    public void create(ContactInfoDto contactInfoDto, Integer resumeId){
        Integer typeId = contactTypeService.getContactTypeIdById(contactInfoDto.getTypeId());
        ContactInfo contactInfo = ConvertHelper.convertToContactInfo(contactInfoDto);
        contactInfoDao.save(contactInfo, resumeId);
    }

    @Override
    public void update(Integer id, ContactInfoForUpdateDto contactInfoDto){
        ContactInfo contactInfo = contactInfoDao.findById(id)
                .orElseThrow(ContactInfoNotFoundException::new);

      if(contactInfoDto.getTypeId() != null){
          Integer typeId = contactTypeService.getContactTypeIdById(contactInfoDto.getTypeId());
      }
        contactInfoDao.update(id, ConvertHelper.correctContactInfoForDao(contactInfo, contactInfoDto));
    }

    @Override
    public void delete(Integer id){
        ContactInfo contactInfo = contactInfoDao.findById(id)
                .orElseThrow(ContactInfoNotFoundException::new);

        contactInfoDao.deleteById(id);
    }

    @Override
    public List<ContactInfoDto> findAllByResumeId(Integer resumeId) {
        return contactInfoDao.findByResumeId(resumeId)
                .orElse(List.of())
                .stream()
                .map(ConvertHelper::convertToContactInfoDto)
                .toList();
    }

    @Override
    public boolean isContactInfoBelongResume(Integer resumeId, Integer contactId){
        if (resumeId == null || contactId == null) {
            return false;
        }
        return contactInfoDao.findResumeIdByContactInfoId(contactId)
                .map(FoundResumeId -> FoundResumeId.equals(resumeId))
                .orElse(false);
    }
}
