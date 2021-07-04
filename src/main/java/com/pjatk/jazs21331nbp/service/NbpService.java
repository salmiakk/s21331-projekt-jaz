package com.pjatk.jazs21331nbp.service;

import com.pjatk.jazs21331nbp.model.Gold;
import com.pjatk.jazs21331nbp.model.NbpResponse;
import com.pjatk.jazs21331nbp.model.Rate;
import com.pjatk.jazs21331nbp.repository.NbpRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class NbpService {
    private final NbpRepository nbpRepository;
    private final RestTemplate restTemplate;

    @Value("${nbpApi:http://api.nbp.pl/api/cenyzlota/}")
    private String nbpApiUrl;

    public NbpService(NbpRepository nbpRepository, RestTemplate restTemplate) {
        this.nbpRepository = nbpRepository;
        this.restTemplate = restTemplate;
    }

    public NbpResponse getAverageRate(String date_from, String date_to){
        ResponseEntity<Rate[]> apiResponse = restTemplate.getForEntity(nbpApiUrl + date_from + "/" + date_to, Rate[].class);
        List<Rate> rates = Arrays.asList(apiResponse.getBody());
        Double average = rates.stream()
                .mapToDouble(Rate::getCena)
                .average()
                .orElse(0.0d);
        Date date = new Date();
        NbpResponse nbpResponse = new NbpResponse(Gold.ZLOTO,date_from,date_to,average, new Timestamp(date.getTime()));
        return nbpRepository.save(nbpResponse);
    }

}
