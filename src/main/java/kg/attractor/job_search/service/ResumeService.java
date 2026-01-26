package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.update.ResumeForUpdateDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResumeService {

    void create(ResumeDto resumeDto);
    void update(Integer id, ResumeForUpdateDto resumeDto, Integer workId, Integer expId, Integer contactId);
    void update(Integer id, ResumeForUpdateDto resumeDto);
    void delete(Integer id);
    List<ResumeDto> findByCategoryId(Integer categoryId);
//    List<ResumeDto> getResumeByAuthor(String author);

    Page<ResumeDto> getResumeByAuthorAuth(Integer page, Integer size);

    Page<ResumeDto> showAllForEmployer(Integer page, Integer size);
    ResumeDto findResumeById(Integer id);
    Integer getResumeIdById(Integer id);
}
