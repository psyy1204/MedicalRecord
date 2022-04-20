package Medical.MedicalRecord.validation;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.form.HospitalForm;

public class HospitalValidation {

    public void updateHospital(Hospital oldHospital, HospitalForm  form) {

        String name = form.getHospitalName();
        if (name != null) oldHospital.setHospitalName(name);

        String address = form.getHospitalAddress();
        if(address != null) oldHospital.setHospitalAddress(address);

        Integer contact = form.getHospitalContact();
        if (contact != null) oldHospital.setHospitalContact(contact);

    }
}
