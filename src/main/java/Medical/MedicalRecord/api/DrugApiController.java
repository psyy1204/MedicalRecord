package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Drug;
import Medical.MedicalRecord.form.DrugForm;
import Medical.MedicalRecord.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drugs")
public class DrugApiController {

    private final DrugService drugService;

    @GetMapping
    public List<Drug> drugus() {
        return drugService.findAll();
    }

    @GetMapping("/{drugId}")
    public Drug drug(@PathVariable("drugId") Long id,
                     HttpServletResponse response) throws IOException {
        Drug drug = drugService.findById(id);
        if(drug == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 약이 없습니다");
            return null;
        } else {
            return drug;
        }
    }

    @PostMapping
    public void addDrug(@RequestBody DrugForm form,
                        HttpServletResponse response) throws IOException{
        if (form.getDrugName() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "찾는 약이 없습니다");
        }else{
            Drug drug = new Drug();
            drug.setDrugName(form.getDrugName());
            drug.setDrugComponent(form.getDrugComponent());
            drugService.add(drug);
        }
    }

    @PatchMapping("/{drugId}")
    public void editDrug(@PathVariable("drugId") Long id,
                         @RequestBody DrugForm form,
                         HttpServletResponse response) throws IOException{
        if (form.getDrugName() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "찾는 약이 없습니다");
        }else{
            drugService.edit(id, form.getDrugName(), form.getDrugComponent());
        }
    }

    @DeleteMapping("/{drugId}")
    public void deleteMember(@PathVariable("drugId") Long id,
                             HttpServletResponse response) throws IOException{
        if(drugService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 회원이 없습니다.");
        }else {
            drugService.delete(id);
        }
    }
}
