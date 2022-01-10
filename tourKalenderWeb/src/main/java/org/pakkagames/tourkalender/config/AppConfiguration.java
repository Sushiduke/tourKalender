package org.pakkagames.tourkalender.config;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@SpringBootConfiguration
@RequiredArgsConstructor
public class AppConfiguration {

    private Map<SerializationFeature, Boolean> serializationFeatureMap =
            Map.of(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @Bean
    public XmlMapper objectMapper() {
        log.info("Configure XmlMapper with project settings: {}", serializationFeatureMap);
        XmlMapper xmlMapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        serializationFeatureMap.forEach(xmlMapper::configure);
        xmlMapper.registerModule(javaTimeModule);

        SimpleModule validationModule = new SimpleModule();
        validationModule.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
                if (deserializer instanceof BeanDeserializer) {
                    return new BeanValidationDeserializer((BeanDeserializer) deserializer);
                }

                return deserializer;
            }
        });
        xmlMapper.registerModule(validationModule);
        return xmlMapper;
    }
}
