package kg.attractor.job_search.controller.api;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ContactInfoDto;
import kg.attractor.job_search.dto.update.ContactInfoForUpdateDto;
import kg.attractor.job_search.service.ContactInfoService;
import kg.attractor.job_search.service.ContactTypeService;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/contactInfo")
@RequiredArgsConstructor
public class ContactInfoRestController {
    private final ContactInfoService contactInfoService;
    private final ContactTypeService ct;
    private final ResumeService resumeService;

    @PostMapping("/{resumeId}")
    public ResponseEntity<Void> create(@PathVariable Integer  resumeId, @Valid @RequestBody ContactInfoDto contactInfoDto){
        Integer id = resumeService.getResumeIdById(resumeId);
        Integer typeId = ct.getContactTypeIdById(contactInfoDto.getTypeId());
        contactInfoService.create(contactInfoDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ContactInfoForUpdateDto contactInfoDto){
        Integer typeId = ct.getContactTypeIdById(contactInfoDto.getTypeId());
        contactInfoService.update(id, contactInfoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        contactInfoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
