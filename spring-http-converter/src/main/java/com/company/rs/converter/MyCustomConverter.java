package com.company.rs.converter;

import com.company.rs.domain.CustomDTO;
import com.company.rs.domain.CustomSubField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MyCustomConverter implements HttpMessageConverter<Object> {


    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.getName().equals("com.company.rs.domain.CustomDTO") &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.getName().equals("com.company.rs.domain.CustomDTO") &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.ALL);
    }

    /*
     * Pretty initial version
     * Following method accept only the below format
     *
     * "firstField_2_firstFieldChild_1_secondField_1_thirdField"
     * */
    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(
                new InputStreamReader(inputMessage.getBody(), Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }

        String[] fields = textBuilder.toString().split("_1_");
        CustomSubField child = new CustomSubField(fields[0].split("_2_")[1]);
        return new CustomDTO(child, fields[1], fields[2]);
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //to be implemented
    }
}
