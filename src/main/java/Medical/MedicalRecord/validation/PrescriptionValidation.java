package Medical.MedicalRecord.validation;

import Medical.MedicalRecord.domain.PrescriptionDrug;
import Medical.MedicalRecord.form.PrescriptionForm;

import java.util.Date;

public class PrescriptionValidation {

    public void updatePrescription(PrescriptionDrug oldPrescription, PrescriptionForm form) {

        Date drugStart = form.getDrugStart();
        if (drugStart != null) oldPrescription.setDurationStart(drugStart);

        Date drugEnd = form.getDrugEnd();
        if (drugEnd != null) oldPrescription.setDurationEnd(drugEnd);

        Integer dosesCount = form.getDosesCount();
        if (dosesCount != null) oldPrescription.setDosesCount(dosesCount);
    }
}
