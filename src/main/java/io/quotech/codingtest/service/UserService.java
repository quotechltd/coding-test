package io.quotech.codingtest.service;

import io.quotech.codingtest.entities.EntityId;
import io.quotech.codingtest.exception.UserAlreadyExistsException;
import io.quotech.codingtest.exception.UserNotFoundException;
import io.quotech.codingtest.integrations.Address;
import io.quotech.codingtest.integrations.CompanyClient;
import io.quotech.codingtest.integrations.Office;
import io.quotech.codingtest.integrations.OfficeClient;
import io.quotech.codingtest.model.AddressLabel;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.mapper.UserMapper;
import io.quotech.codingtest.model.UserMetadata;
import io.quotech.codingtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final OrganisationIdProvider organisationIdProvider;
  private final CompanyClient companyClient;
  private final OfficeClient officeClient;

  public UserService(UserRepository userRepository,
                     UserMapper userMapper,
                     OrganisationIdProvider organisationIdProvider,
                     CompanyClient companyClient,
                     OfficeClient officeClient) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.organisationIdProvider = organisationIdProvider;
    this.companyClient = companyClient;
    this.officeClient = officeClient;
  }

  public User createUser(User user) throws UserAlreadyExistsException {
    String organisationId = organisationIdProvider.getOrganisationId();

    EntityId id = EntityId.builder()
            .withOrganisationId(organisationId)
            .withId(user.getUserId())
            .build();

    if (userRepository.existsById(id)) {
      throw new UserAlreadyExistsException();
    }

    io.quotech.codingtest.entities.User entity = userMapper.map(organisationId, user);
    return userMapper.map(userRepository.save(entity));
  }

  public User getUser(String userId) throws UserNotFoundException {
    String organisationId = organisationIdProvider.getOrganisationId();

    EntityId id = EntityId.builder()
      .withOrganisationId(organisationId)
      .withId(userId)
      .build();

    return userRepository.findById(id)
      .map(userMapper::map)
      .orElseThrow(UserNotFoundException::new);
  }

  public List<User> getAllUsers() {
    String organisationId = organisationIdProvider.getOrganisationId();

    return userRepository.getAllByOrganisationId(organisationId)
            .stream()
            .map(userMapper::map)
            .collect(Collectors.toList());
  }

  public List<AddressLabel> getAllAddresses() {
    String organisationId = organisationIdProvider.getOrganisationId();

    List<User> users = userRepository.getAllByOrganisationId(organisationId)
            .stream()
            .map(userMapper::map)
            .collect(Collectors.toList());

    List<AddressLabel> addresses = new ArrayList<>();
    List<Office> offices = officeClient.getAddressesForOrganisation(organisationId);

    for (User user: users) {
      UserMetadata metadata = user.getMetadata();

      Address address = offices.stream()
              .filter(x -> x.getOfficeId().equals(metadata.getOfficeId()))
              .findFirst()
              .get()
              .getAddress();

      AddressLabel.Builder builder = AddressLabel.builder()
              .withName(metadata.getFirstName() + " " + metadata.getLastName())
              .withCompany(companyClient.getCompanyName(metadata.getCompanyId()))
              .withFirstLine(address.getFirstLine())
              .withSecondLine(address.getSecondLine())
              .withCity(address.getCity())
              .withPostcode(address.getPostcode());
    }

    return addresses;
  }
}
