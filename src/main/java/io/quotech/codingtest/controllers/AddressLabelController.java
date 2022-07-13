package io.quotech.codingtest.controllers;

import io.quotech.codingtest.integrations.CompanyClient;
import io.quotech.codingtest.model.AddressLabel;
import io.quotech.codingtest.model.User;
import io.quotech.codingtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/address")
public class AddressLabelController {

    private final UserService userService;

    public AddressLabelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AddressLabel>> getAll() {
        return ResponseEntity.ok(
                userService.getAllAddresses());
    }
}
