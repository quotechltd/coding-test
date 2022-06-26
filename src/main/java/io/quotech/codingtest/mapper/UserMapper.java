package io.quotech.codingtest.mapper;

import io.quotech.codingtest.entities.EntityId;
import io.quotech.codingtest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  private final UserMetadataMapper userMetadataMapper;
  private final UserRoleMapper userRoleMapper;

  public UserMapper(UserMetadataMapper userMetadataMapper,
                    UserRoleMapper userRoleMapper) {
    this.userMetadataMapper = userMetadataMapper;
    this.userRoleMapper = userRoleMapper;
  }

  public User map(io.quotech.codingtest.entities.User entity) {
    return User.builder()
            .withUserId(entity.getId().getId())
            .withMetadata(userMetadataMapper.toModel(entity.getMetadata()))
            .withRole(userRoleMapper.toModel(entity.getRole()))
            .build();
  }

  public io.quotech.codingtest.entities.User map(String clientId, User model) {
    EntityId entityId = EntityId.builder()
            .withClientId(clientId)
            .withId(model.getUserId())
            .build();

    return io.quotech.codingtest.entities.User.builder()
            .withId(entityId)
            .withMetadata(userMetadataMapper.toEntity(model.getMetadata()))
            .withRole(userRoleMapper.toEntity(model.getRole()))
            .build();
  }
}
