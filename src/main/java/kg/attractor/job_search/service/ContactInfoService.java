package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ContactInfoDto;

import java.util.List;

public interface ContactInfoService {
    void create(ContactInfoDto contactInfoDto, Integer resumeId);
    void update(Integer id, ContactInfoDto contactInfoDto);
    void delete(Integer id);
    List<ContactInfoDto> findAllByResumeId(Integer resumeId);
}
