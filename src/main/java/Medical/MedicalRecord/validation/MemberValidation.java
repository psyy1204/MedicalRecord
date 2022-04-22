package Medical.MedicalRecord.validation;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.MemberForm;

public class MemberValidation {

    public void updateMember(Member oldMember, MemberForm form) {
        String name = form.getUsername();
        if(name != null) oldMember.setUserName(name);

        Integer age = form.getAge();
        if(age != null) oldMember.setAge(age);

        Gender gender = form.getGender();
        if (gender != null) oldMember.setGender(gender);

        String nickName  = form.getNickName();
        if (nickName != null) oldMember.setNickName(nickName);

        Integer height = form.getHeight();
        if (height != null) oldMember.setHeight(height);

        Integer weight = form.getWeight();
        if (weight != null) oldMember.setWeight(weight);
    }
}
