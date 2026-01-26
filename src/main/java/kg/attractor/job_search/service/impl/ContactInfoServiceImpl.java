package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.ContactInfoDto;
import kg.attractor.job_search.dto.update.ContactInfoForUpdateDto;
import kg.attractor.job_search.exceptions.ContactInfoNotFoundException;
import kg.attractor.job_search.model.ContactInfo;
import kg.attractor.job_search.model.ContactType;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.repository.ContactInfoRepository;
import kg.attractor.job_search.service.ContactInfoService;
import kg.attractor.job_search.service.ContactTypeService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService{
    private final ContactInfoRepository repository;
    private final ContactTypeService typeService;

    @Override
    public void create(ContactInfoDto contactInfoDto, Resume resume){
        ContactType type = typeService.findById(contactInfoDto.getTypeId());
        ContactInfo contactInfo = ConvertHelper.convertToContactInfo(contactInfoDto, resume);
        contactInfo.setType(type);

        repository.save(contactInfo);
    }

    @Override
    public void update(Integer id, ContactInfoForUpdateDto contactInfoDto){
        ContactInfo contactInfo = repository.findById(id)
                .orElseThrow(ContactInfoNotFoundException::new);

      if(contactInfoDto.getTypeId() != null){
          contactInfo.setType(typeService.findById(contactInfoDto.getTypeId()));
      }
        repository.save(ConvertHelper.correctContactInfoForDao(contactInfo, contactInfoDto));
    }

    @Override
    public void delete(Integer id){
        ContactInfo contactInfo = repository.findById(id)
                .orElseThrow(ContactInfoNotFoundException::new);

        repository.delete(contactInfo);
    }

    @Override
    public List<ContactInfoDto> findAllByResumeId(Integer resumeId) {
        return repository.findContactInfosByResume_Id(resumeId)
                .stream()
                .map(ConvertHelper::convertToContactInfoDto)
                .toList();
    }

    @Override
    public boolean isContactInfoBelongResume(Integer resumeId, Integer contactId){
        if (resumeId == null || contactId == null) {
            return false;
        }
        return repository.findResumeIdByContactInfoId(contactId)
                .map(FoundResumeId -> FoundResumeId.equals(resumeId))
                .orElse(false);
    }
}
