package com.sap.cloud.sdk.tutorial.controllers;

import java.util.List;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.sdk.cloudplatform.connectivity.DefaultDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.sap.vdm.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.tutorial.utils.GetBusinessPartnersCommand;

@RestController
public class BusinessPartnerController {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(BusinessPartnerController.class);

    @RequestMapping(value = "/getBusinessPartners", method = RequestMethod.GET)
    public String getBusinessPartners() {
        
        // Destination to the local Mock Server
        final HttpDestination destination = DefaultDestination.builder()
                                                .property("Name", "mydestination")
                                                .property("URL", "http://localhost:8081")
                                                .property("Type", "HTTP")
                                                .property("Authentication", "NoAuthentication")
                                                .build().asHttp();

        // Destination to the SAP API Business Hub Sandbox
        // final HttpDestination destination = DefaultDestination.builder()
        //                                         .property("Name", "mydestination")
        //                                         .property("URL", "https://sandbox.api.sap.com/s4hanacloud")
        //                                         .property("Type", "HTTP")
        //                                         .property("Authentication", "NoAuthentication")
        //                                         .build().asHttp();

        // Destination to a SAP S/4HANA Cloud (public edition) tenant
        //final HttpDestination destination = DefaultDestination.builder()
        //                                         .property("Name", "mydestination")
        //                                         .property("URL", "https://my######.s4hana.ondemand.com")
        //                                         .property("Type", "HTTP")
        //                                         .property("Authentication", "BasicAuthentication")
        //                                         .property("User", "ADDRESS_MANAGER_###")
        //                                         .property("Password", "WelcomeToTheClouds1!")
        //                                         .build().asHttp();

        final List<BusinessPartner> businessPartners = new GetBusinessPartnersCommand(destination).execute();

        logger.info(String.format("Found %d business partner(s).", businessPartners.size()));

        return new Gson().toJson(businessPartners);
    }
    
}