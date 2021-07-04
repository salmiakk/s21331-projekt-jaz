package com.pjatk.jazs21331nbp.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class NbpResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "This it the ID of the response", required = true, value = "id of the response", example = "1")
    private Long id;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Type of gold", required = true, value = "gold type", example = "ZLOTO")
    private Gold gold;
    @ApiModelProperty(notes = "Start date for your exchange rate, in YYYY-mm-dd format", required = true, value = "time period start date", example = "2020-10-01")
    private String fromDate;
    @ApiModelProperty(notes = "End date for your exchange rate, in YYYY-mm-dd format", required = true, value = "time period end date", example = "2020-10-02")
    private String toDate;
    @ApiModelProperty(notes = "Exchange rate for the given period", required = true, value = "Exchange rate for the given period", example = "199.30")
    private double exchangeRate;
    @ApiModelProperty(notes = "Timestamp of the request", required = true, value = "Timestamp of the request", example = "2021-07-04T13:49:31.449+00:00")
    private Timestamp requestDate;
    public NbpResponse() {
    }

    public NbpResponse(Gold gold, String fromDate, String toDate, double exchangeRate, Timestamp requestDate) {
        this.gold = gold;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.exchangeRate = exchangeRate;
        this.requestDate = requestDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }
}
