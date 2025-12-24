package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.model.CustomErrorResponse;
import kg.attractor.job_search.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public CustomErrorResponse makeResponse(Exception e) {
        String msg = e.getMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setError(msg);
        Map<String, List<String>> reasons = new HashMap<>();
        reasons.put("reasons", Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                .toList());
        errorResponse.setReasons(reasons);
        return errorResponse;
    }

    @Override
    public CustomErrorResponse makeResponse(BindingResult bindingResult) {
        Map<String, List<String>> reasons = new HashMap<>();
        bindingResult.getFieldErrors().stream()
                .filter(e -> e.getDefaultMessage() != null)
                .forEach(e -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(e.getDefaultMessage());
                    if (!reasons.containsKey(e.getField())) {
                        reasons.put(e.getField(), errors);
                    }
                });

        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setError("Validation Error");
        errorResponse.setReasons(reasons);
        return errorResponse;
    }
}
