package kg.attractor.job_search.controller;

import kg.attractor.job_search.model.RespondedApplicant;
import kg.attractor.job_search.service.RespondedApplicantService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/responses")
@RequiredArgsConstructor
public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;
    private final UserHelper userHelper;
    private final ResumeService resumeService;

    @PostMapping("/respond")
    public String respond(@RequestParam Integer vacancyId,
                          @RequestParam Integer resumeId) {

        respondedApplicantService.respond(vacancyId, resumeId);

        return "redirect:/vacancies/" + vacancyId;
    }

    @GetMapping("/my")
    public String myResponses(Model model) {
        String email = userHelper.getAuthUser().getEmail();

        model.addAttribute("responses",
                respondedApplicantService.getResponsesByUser(email));

        return "responses/myResponses";
    }

    @GetMapping("/vacancy/{id}")
    public String responsesForVacancy(@PathVariable Integer id, Model model) {

        model.addAttribute("responses",
                respondedApplicantService.getResponsesByVacancy(id));

        model.addAttribute("vacancyId", id);

        return "responses/vacancyResponses";
    }

    @PostMapping("/confirm")
    public String confirmResponse(@RequestParam Integer responseId) {
        RespondedApplicant response = respondedApplicantService.findById(responseId);

        if (!response.getVacancy().getAuthor().getEmail().equals(userHelper.getAuthUser().getEmail())) {
            throw new RuntimeException("Вы не можете подтверждать отклики на чужую вакансию");
        }

        respondedApplicantService.confirmResponse(responseId);

        return "redirect:/responses/vacancy/" + response.getVacancy().getId();
    }
}
