package service.inter;

import model.PasswordParametersDTO;
import model.ResponseApi;

public interface PasswordGeneratorSvcInter {
    public ResponseApi generatePassword(PasswordParametersDTO parametersDTO);
}
