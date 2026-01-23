package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ContactInfoDto;
import kg.attractor.job_search.dto.update.ContactInfoForUpdateDto;
import kg.attractor.job_search.model.Resume;

import java.util.List;

public interface ContactInfoService {
    void create(ContactInfoDto contactInfoDto, Resume resume);
    void update(Integer id, ContactInfoForUpdateDto contactInfoDto);
    void delete(Integer id);
    List<ContactInfoDto> findAllByResumeId(Integer resumeId);

    boolean isContactInfoBelongResume(Integer resumeId, Integer contactId);
}
