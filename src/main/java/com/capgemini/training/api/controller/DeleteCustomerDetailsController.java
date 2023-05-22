package com.capgemini.training.api.controller;

import com.capgemini.training.api.service.DeleteCustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/capgemini/training/customer"})
@Tag(name = "Customers")
public class DeleteCustomerDetailsController {

  private final DeleteCustomerDetailsService deleteCustomerDetailsService;
  private static final String NO_CONTENT = "204";

  public DeleteCustomerDetailsController(
      DeleteCustomerDetailsService deleteCustomerDetailsService) {
    this.deleteCustomerDetailsService = deleteCustomerDetailsService;
  }

  @Operation(summary = "Service for delete customer.", description = "Service for delete customer.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = NO_CONTENT,
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
      })
  @DeleteMapping(value = "/{customerId}")
  public ResponseEntity<Void> deleteCustomerDetails(@PathVariable("customerId") @NotEmpty String customerId) {
    deleteCustomerDetailsService.deleteCustomerDetails(customerId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
