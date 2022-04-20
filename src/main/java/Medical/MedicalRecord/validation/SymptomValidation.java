package Medical.MedicalRecord.validation;

import Medical.MedicalRecord.domain.Symptom;
import Medical.MedicalRecord.form.SymptomForm;

import java.util.Date;

public class SymptomValidation {

    public void updateSymptom(Symptom oldSymptom, SymptomForm form) {

        String simpleSymptom = form.getSimpleSymptom();
        if (simpleSymptom != null) oldSymptom.setSimpleSymptom(simpleSymptom);

        String detailSymptom = form.getDetailSymptom();
        if (detailSymptom != null) oldSymptom.setDetailSymptom(detailSymptom);

        Date startDate = form.getStartDate();
        if (startDate != null) oldSymptom.setStartDate(startDate);

        Float bodyTemperature = form.getBodyTemperature();
        if (bodyTemperature != null) oldSymptom.setBodyTemperature(bodyTemperature);

        Integer systolic = form.getSystolic();
        if (systolic != null) oldSymptom.setSystolic(systolic);

        Integer diastolic = form.getDiastolic();
        if (diastolic != null) oldSymptom.setDiastolic(diastolic);

        Integer oxygenSaturation = form.getOxygenSaturation();
        if (oxygenSaturation != null) oldSymptom.setOxygenSaturation(oxygenSaturation);

    }

}
