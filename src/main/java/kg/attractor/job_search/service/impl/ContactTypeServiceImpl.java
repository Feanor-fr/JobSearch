package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.exceptions.ContactTypeNotFoundException;
import kg.attractor.job_search.model.ContactType;
import kg.attractor.job_search.repository.ContactTypeRepository;
import kg.attractor.job_search.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {
  private final ContactTypeRepository repository;

    @Override
    public Integer getContactTypeIdById(Integer id){
        ContactType ct = repository
                .findById(id)
                .orElseThrow(ContactTypeNotFoundException::new);

        return ct.getId();
    }

    @Override
    public ContactType findById(Integer id){
        return repository
                .findById(id)
                .orElseThrow(ContactTypeNotFoundException::new);

    }
}
