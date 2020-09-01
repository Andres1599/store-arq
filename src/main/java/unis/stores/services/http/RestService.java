package unis.stores.services.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import unis.stores.entities.*;

import java.util.Collections;
import java.util.Date;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public boolean cancelRequest(int id, String ip) {
        String url = "http://" + ip + "/request/cancel/" + id;

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // use `exchange` method for HTTP call
        ResponseEntity<Object> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            return false;
        }
    }

    public ProductFabric[] getProductList(String ip) {
        String url = "http://" + ip + "/api/factory/part/";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity request = new HttpEntity(headers);

        // use `exchange` method for HTTP call
        ResponseEntity<ProductFabric[]> response = this.restTemplate.exchange(url, HttpMethod.GET, request, ProductFabric[].class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public Date createRequest(Request request, String ip) {
        String url = "http://" + ip + "/api/factory/store/order/";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<Request> httpRequest = new HttpEntity(request, headers);

        for (ProductRequest productRequest :
                request.getProductRequests()) {
            productRequest.setRequest(null);
        }

        // use `exchange` method for HTTP call
        ResponseEntity<CreateRequestResponse> response = this.restTemplate.exchange(url, HttpMethod.POST, httpRequest, CreateRequestResponse.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getData();
        } else {
            return null;
        }
    }
}