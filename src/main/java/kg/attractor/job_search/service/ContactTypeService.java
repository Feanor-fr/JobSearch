package kg.attractor.job_search.service;

import kg.attractor.job_search.model.ContactType;

public interface ContactTypeService {
    Integer getContactTypeIdById(Integer id);

    ContactType findById(Integer id);
}
