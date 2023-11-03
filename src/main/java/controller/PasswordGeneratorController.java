package controller;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.PasswordParametersDTO;
import model.ResponseApi;
import service.inter.PasswordGeneratorSvcInter;

@Path("/password-generator")
public class PasswordGeneratorController {
    @Inject
    PasswordGeneratorSvcInter generator;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get-new-password")
    public ResponseApi generatePassword(@RequestBody PasswordParametersDTO parametersDTO) {
        return generator.generatePassword(parametersDTO);
    }
}
