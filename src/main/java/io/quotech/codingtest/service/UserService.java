package io.quotech.codingtest.service;

import io.quotech.codingtest.entities.EntityId;
import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.integrations.CompanyClient;
import io.quotech.codingtest.integrations.Office;
import io.quotech.codingtest.integrations.OfficeClient;
import io.quotech.codingtest.model.AddressLabel;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.mapper.UserMapper;
import io.quotech.codingtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final ClientIdProvider clientIdProvider;

  private final CompanyClient companyClient;
  private final OfficeClient officeClient;

  public UserService(UserRepository userRepository,
                     UserMapper userMapper,
                     ClientIdProvider clientIdProvider,
                     CompanyClient companyClient,
                     OfficeClient officeClient) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.clientIdProvider = clientIdProvider;
    this.companyClient = companyClient;
    this.officeClient = officeClient;
  }

  public User createUser(User user) throws UserAlreadyExistsException {
    String clientId = clientIdProvider.getClientId();

    EntityId id = EntityId.builder()
            .withClientId(clientId)
            .withId(user.getUserId())
            .build();

    if (userRepository.existsById(id)) {
      throw new UserAlreadyExistsException();
    }

    io.quotech.codingtest.entities.User entity = userMapper.map(clientId, user);
    return userMapper.map(userRepository.save(entity));
  }

  public User getUser(String userId) throws UserNotFoundException {
    String clientId = clientIdProvider.getClientId();

    EntityId id = EntityId.builder()
      .withClientId(clientId)
      .withId(userId)
      .build();

    return userRepository.findById(id)
      .map(userMapper::map)
      .orElseThrow(UserNotFoundException::new);
  }

  public List<User> getAllUsers() {

    return userRepository.getAllByClientId(clientIdProvider.getClientId())
            .stream()
            .map(userMapper::map)
            .collect(Collectors.toList());
  }

  public List<AddressLabel> getAllAddressLabels() {

    List<AddressLabel> addressLabelList = new ArrayList<>();
    List<User> userList = userRepository.findAll()
            .stream()
            .map(userMapper::map)
            .collect(Collectors.toList());

    List<Office> officeList = officeClient.getAddressForClient(clientIdProvider.getClientId())
            .stream().collect(Collectors.toList());

    userList.stream().forEach(u -> {
      String companyName = companyClient.getCompanyName(u.getMetadata().getCompanyId());
      Optional<Office> officeOpt = officeList.stream().filter(o -> o.getOfficeId().equals(u.getMetadata().getOfficeId())).findAny();
      addressLabelList.add(AddressLabel.builder().withName(companyName).withCity(officeOpt.get().getAddress().getCity()).withName(u.getMetadata().getLastName()).build());

    });

    return addressLabelList;

  }

}
