package io.quotech.codingtest.mapper;

import io.quotech.codingtest.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public io.quotech.codingtest.entities.UserRole toEntity(UserRole model) {
        switch (model) {
            case internal:
                return io.quotech.codingtest.entities.UserRole.internal;
            case broker:
                return io.quotech.codingtest.entities.UserRole.broker;
            case reinsurer:
                return io.quotech.codingtest.entities.UserRole.reinsurer;
            default:
                throw new UnsupportedOperationException("UserRole not supported: " + model);
        }
    }

    public UserRole toModel(io.quotech.codingtest.entities.UserRole entity) {
        switch (entity) {
            case internal:
                return UserRole.internal;
            case broker:
                return UserRole.broker;
            case reinsurer:
                return UserRole.reinsurer;
            default:
                throw new UnsupportedOperationException("UserRole not supported: " + entity);
        }
    }
}
