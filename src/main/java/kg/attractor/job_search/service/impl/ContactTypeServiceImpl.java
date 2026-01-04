package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ContactTypesDao;
import kg.attractor.job_search.exceptions.ContactTypeNotFoundException;
import kg.attractor.job_search.model.ContactType;
import kg.attractor.job_search.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypesDao contactTypesDao;

    @Override
    public Integer getContactTypeIdById(Integer id){
        ContactType ct = contactTypesDao.findById(id).orElseThrow(ContactTypeNotFoundException::new);
        return ct.getId();
    }
}
