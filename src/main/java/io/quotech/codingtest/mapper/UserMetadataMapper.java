package io.quotech.codingtest.mapper;

import io.quotech.codingtest.model.UserMetadata;
import org.springframework.stereotype.Component;

@Component
public class UserMetadataMapper {
    
    public UserMetadata toModel(io.quotech.codingtest.entities.UserMetadata entity) {
        return UserMetadata.builder()
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withEmailAddress(entity.getEmailAddress())
                .withCompanyId(entity.getCompanyId())
                .withOfficeId(entity.getOfficeId())
                .build();
    }

    public io.quotech.codingtest.entities.UserMetadata toEntity(UserMetadata model) {
        return io.quotech.codingtest.entities.UserMetadata.builder()
                .withFirstName(model.getFirstName())
                .withLastName(model.getLastName())
                .withEmailAddress(model.getEmailAddress())
                .withCompanyId(model.getCompanyId())
                .withOfficeId(model.getOfficeId())
                .build();
    }
}
