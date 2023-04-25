package com.capgemini.training.api.controller;

import com.capgemini.training.api.model.CustomerDetails;
import com.capgemini.training.api.model.DocumentType;
import java.util.UUID;

public class CustomerDetailsMother{

    private String customerId;
    private DocumentType documentType;
    private String documentNumber;
    private String name;
    private String surName;
    private String lastName;
    private String country;
    private String telephone;

    public CustomerDetailsMother() {
      this.customerId= UUID.randomUUID().toString();
      this.documentType=DocumentType.DNI;
      this.documentNumber="235234123M";
      this.name="Marck";
      this.surName="paquito";
      this.lastName="fernandez";
      this.country="ES";
      this.telephone="688774455";
    }

    public static CustomerDetailsMother init() {
      return new CustomerDetailsMother();
    }


    public CustomerDetailsMother withCustomerId(String customerId) {
      this.customerId = customerId;
      return this;
    }

    public CustomerDetailsMother withDocumentType(DocumentType documentType) {
      this.documentType = documentType;
      return this;
    }

    public CustomerDetailsMother withDocumentNumber(String documentNumber) {
      this.documentNumber = documentNumber;
      return this;
    }

    public CustomerDetailsMother withName(String name) {
      this.name = name;
      return this;
    }

    public CustomerDetailsMother withSurName(String surName) {
      this.surName = surName;
      return this;
    }

    public CustomerDetailsMother withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public CustomerDetailsMother withCountry(String country) {
      this.country = country;
      return this;
    }

    public CustomerDetailsMother withTelephone(String telephone) {
      this.telephone = telephone;
      return this;
    }

    public CustomerDetails get() {
      CustomerDetails customerDetails = new CustomerDetails();
      customerDetails.setCustomerId(this.customerId);
      customerDetails.setDocumentType(this.documentType);
      customerDetails.setDocumentNumber(this.documentNumber);
      customerDetails.setName(this.name);
      customerDetails.setSurName(this.surName);
      customerDetails.setLastName(this.lastName);
      customerDetails.setCountry(this.country);
      customerDetails.setTelephone(this.telephone);
      return customerDetails;
    }
  }
