package kg.attractor.job_search.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.job_search.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;


    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(Model model, HttpServletRequest request){
       model.addAttribute("status", HttpStatus.NOT_FOUND.value());
       model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
       model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(WrongRoleException.class)
    public String wrongRole(
            WrongRoleException ex,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", ex.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String businessError(RuntimeException r,Model model, HttpServletRequest request){
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", r.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<CustomErrorResponse> handleNotFound(NoSuchElementException e) {
//        return new ResponseEntity<>(errorService.makeResponse(e), HttpStatus.NOT_FOUND);
//        return errorService.makeResponse(e);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<CustomErrorResponse> validationHandler(MethodArgumentNotValidException e) {
//        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<CustomErrorResponse> handleBusinessErrors(RuntimeException r) {
//        return new ResponseEntity<>(errorService.makeResponse(r), HttpStatus.BAD_REQUEST);
//    }
}
