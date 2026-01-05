package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ResumeDto;

import java.util.List;

public interface ResumeService {

    void create(ResumeDto resumeDto);
    void update(Integer id, ResumeDto resumeDto, Integer workId, Integer expId, Integer contactId);
    void delete(Integer id);
    List<ResumeDto> findByCategoryId(Integer categoryId);
    List<ResumeDto> getResumeByAuthor(String author);
    List<ResumeDto> showAll();
    ResumeDto findResumeById(Integer id);
    Integer getResumeIdById(Integer id);
}
