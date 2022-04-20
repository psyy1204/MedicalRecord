package Medical.MedicalRecord.validation;

import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.form.MedicalRecordForm;

public class MedicalRecordValidation {

    public void updateRecord(MedicalRecord oldRecord ,MedicalRecordForm form) {

        String doctorName = form.getDoctorName();
        if(doctorName != null) oldRecord.setDoctorName(doctorName);

        String diagnosis= form.getDiagnosis();
        if (diagnosis != null) oldRecord.setDiagnosis(diagnosis);

        String etc = form.getEtc();
        if(etc != null) oldRecord.setEtc(etc);

        Integer price = form.getPrice();
        if (price!=null) oldRecord.setPrice(price);

        String medicalDepartmentCode = form.getMedicalDepartmentCode();
        if (medicalDepartmentCode != null) oldRecord.setMedicalDepartmentCode(medicalDepartmentCode);
    }
}
