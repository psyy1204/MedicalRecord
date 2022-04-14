//package Medical.MedicalRecord.service;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class StringToDateConverter
//        implements Converter<String, Date> {
//
//    @Override
//    public Date convert(String source) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyy-MM-dd");
//        return format.parse(source);
//    }
//}