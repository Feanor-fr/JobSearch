package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.service.RespondedApplicantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    @Override
    public ResponseEntity<Void> respond(Integer vacancyId) {
        // TODO логика будет добавлена позже
        return null;
    }
}
