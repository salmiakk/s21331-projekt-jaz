package com.pjatk.jazs21331nbp.controller;

import com.pjatk.jazs21331nbp.exceptions.NotFoundException;
import com.pjatk.jazs21331nbp.model.NbpResponse;
import com.pjatk.jazs21331nbp.service.NbpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nbp")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }
    @ApiOperation(value = "Get an exchange rate for gold, for given dates",
            response = NbpResponse.class,
            notes = "This method will return an exchange rate for GOLD for a given time period, or HTTP 404 if the given time period is not found/incorrect.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No such time period - eg. wrong dates"),
            @ApiResponse(code = 400, message = "Bad request parameters")
    })
    @GetMapping("/rate/{date_from}/{date_to}")
    public ResponseEntity<NbpResponse> getAverageRate(@PathVariable String date_from, @PathVariable String date_to){
        try {
            return ResponseEntity.ok(nbpService.getAverageRate(date_from, date_to));
        } catch(NotFoundException e){
            if(e.getMessage().equals("NBP API returned 404!")){
                return ResponseEntity.notFound().build();
            }
            if(e.getMessage().equals("NBP API returned 400!")){
                return ResponseEntity.badRequest().build();
            }else{
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}
