package Medical.MedicalRecord.unuse;

import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarService {

    private final CalenderRepository calendarRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Transactional
    public List<calendar> getCalendarData() {

        List<MedicalRecord> recordList = medicalRecordRepository.findAll();
        for (int i = 0; i < recordList.size(); i++){
            MedicalRecord record = recordList.get(i);
            Date start = record.getVisitedDate();
            String diagnosis = record.getDiagnosis();
            calendar calendar = new calendar();
            calendar.setStart(start);
            calendar.setDiagnosis(diagnosis);
            calendarRepository.save(calendar);
        }
        return calendarRepository.findAll();
    }
}
