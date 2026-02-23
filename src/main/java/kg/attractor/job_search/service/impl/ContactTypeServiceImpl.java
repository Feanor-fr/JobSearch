package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.ContactTypeDto;
import kg.attractor.job_search.exceptions.ContactTypeNotFoundException;
import kg.attractor.job_search.model.ContactType;
import kg.attractor.job_search.repository.ContactTypeRepository;
import kg.attractor.job_search.service.ContactTypeService;
import kg.attractor.job_search.utils.ConvertHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<ContactTypeDto> showAll(){
        return repository.findAll()
                .stream()
                .map(ConvertHelper::convertToContactTypeDto)
                .toList();
    }

    @Override
    public Map<String, String> contactTypeIdAndNameLikeMap(){
        return showAll().stream()
                .collect(Collectors.toMap(с ->
                                String.valueOf(с.getId()),
                        ContactTypeDto::getType));
    }
}
