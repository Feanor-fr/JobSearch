package kg.attractor.job_search.service;

import kg.attractor.job_search.model.CustomErrorResponse;
import org.springframework.validation.BindingResult;

public interface ErrorService {

    CustomErrorResponse makeResponse(Exception e);
    CustomErrorResponse makeResponse(BindingResult bindingResult);
}
