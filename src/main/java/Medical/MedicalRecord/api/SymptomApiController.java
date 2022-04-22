package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Symptom;
import Medical.MedicalRecord.form.SymptomForm;
import Medical.MedicalRecord.service.SymptomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/symptoms")
public class SymptomApiController {

    private final SymptomService symptomService;

    /**
     *
     * @return 전체 증상 리스트
     */
    @GetMapping
    public List<Symptom> symptoms() {
        return symptomService.findAll();
    }

    /**
     *
     * @param id
     * @return id에 해당하는 증상
     */
    @GetMapping("/{symptomId}")
    public Symptom symptom(@PathVariable("symptomId") Long id,
                           HttpServletResponse response) throws IOException {
        Symptom symptom = symptomService.findById(id);
        if(symptom == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 증상이 없습니다");
            return null;
        } else {
            return symptom;
        }
    }

    @PostMapping
    public void addSymptom(@RequestBody SymptomForm form,
                            HttpServletResponse response) throws IOException{

        if(form.getSimpleSymptom() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "이름과 이메일은 필수입니다");
        } else {
            Symptom symptom = new Symptom();
            symptom.setSimpleSymptom(form.getSimpleSymptom());
            symptom.setDetailSymptom(form.getDetailSymptom());
            symptom.setStartDate(form.getStartDate());
            symptom.setPulse(form.getPulse());
            symptom.setOxygenSaturation(form.getOxygenSaturation());
            symptom.setBodyTemperature(form.getBodyTemperature());
            symptom.setSystolic(form.getSystolic());
            symptom.setDiastolic(form.getDiastolic());
            symptom.setCreatedDate(LocalDateTime.now());
            symptom.setUpdatedDate(LocalDateTime.now());

            symptomService.add(symptom);
        }
    }

    /**
     * @param id
     * 증상 수정
     */
    @PatchMapping("/{symptomId}")
    public void editSymptom(@PathVariable("symptomId") Long id,
                            @RequestBody SymptomForm form,
                            HttpServletResponse response) throws IOException {
        if(symptomService.findById(id) == null  ) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 증상 없습니다.");
        } else symptomService.updateSymptom(form, id);
    }

    //딜리트 보류


}
