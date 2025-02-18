package br.com.devedojo.demo.handler;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
@ControllerAdvice
public class RestResponseExceptionHander extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        System.out.println("Inside hasError");
        super.handleError(response);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("Doing something with status code"+response.getStatusCode());
        System.out.println("Doing something with body"+ IOUtils.toString(response.getBody(),"UTF-8"));
        return super.hasError(response);
    }
}
