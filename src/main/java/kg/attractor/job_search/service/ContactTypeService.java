package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ContactTypeDto;
import kg.attractor.job_search.model.ContactType;

import java.util.List;
import java.util.Map;

public interface ContactTypeService {
    Integer getContactTypeIdById(Integer id);

    ContactType findById(Integer id);

    List<ContactTypeDto> showAll();

    Map<String, String> contactTypeIdAndNameLikeMap();
}
