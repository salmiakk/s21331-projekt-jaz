package com.pjatk.jazs21331nbp.config;

import com.pjatk.jazs21331nbp.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR ||
                        clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
        );
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if(clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR){
            if(clientHttpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new NotFoundException("NBP API returned 503!");
            }
            if(clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new NotFoundException("NBP API returned 404!");
            }
            if(clientHttpResponse.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new NotFoundException("NBP API returned 400!");
            }
        } else if(clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){
            throw new NotFoundException("NBP API returned 50x!");
        }
    }
}
