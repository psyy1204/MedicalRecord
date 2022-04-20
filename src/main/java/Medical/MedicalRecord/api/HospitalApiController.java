package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.form.HospitalForm;
import Medical.MedicalRecord.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospitals")
public class HospitalApiController {

    private final HospitalService hospitalService;

    /**
     *
     * @return 전제 병원
     */
    @GetMapping
    public List<Hospital> hospitals(){
        return hospitalService.findAll();
    }

    /**
     * @param id
     * @return id 해당 병원
     */
    @GetMapping("/{hospitalId}")
    public Hospital hospital(@PathVariable("hospitalId") Long id,
                             HttpServletResponse response) throws IOException {
        Hospital hospital = hospitalService.findById(id);
        if(hospital == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 병원이 없습니다.");
            return null;
        } else {
            return hospital;
        }
    }

    @PostMapping
    public void addHospital(@RequestBody HospitalForm form,
                            HttpServletResponse response) throws IOException{

        if(form.getHospitalName() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 병원이 없습니다.");
        } else {
            Hospital hospital = new Hospital();
            hospital.setHospitalName(form.getHospitalName());
            hospital.setHospitalContact(form.getHospitalContact());
            hospital.setHospitalAddress(form.getHospitalAddress());

            hospitalService.add(hospital);
        }

    }
    
    @PatchMapping("/{hospitalId}")
    public void editHospital(@PathVariable("hospitalId") Long id,
                             @RequestBody HospitalForm form,
                             HttpServletResponse response) throws IOException {
        if(hospitalService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 병원이 없습니다.");
        } else {
            hospitalService.updateHospital(form, id);
        }
    }

    /**
     * 조인테이블때매 삭제 안되는거 어케해결할건지!
     * @param id
     * @param response
     * @throws IOException
     */
    @DeleteMapping("/{hospitalId}")
    public void deleteHospital(@PathVariable("hospitalId") Long id,
                               HttpServletResponse response) throws IOException {
        if(hospitalService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "찾는 병원이 없습니다.");
        } else {
            hospitalService.delete(id);
        }
    }
}
