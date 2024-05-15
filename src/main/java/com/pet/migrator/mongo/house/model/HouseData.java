package com.pet.migrator.mongo.house.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseData implements Serializable {
    private String country;
    private String countryIsoCode;
    private String region;
    private String regionFiasId;
    private String cityType;
    private String city;
    private String cityFiasId;
    private String settlementType;
    private String settlement;
    private String settlementFiasId;
    private String streetType;
    private String street;
    private String streetFiasId;
    private String houseType;
    private String house;
    private String blockType;
    private String block;
    private String houseFiasId;
    private String fiasLevel;
    private String cityKladrId;
    private String timeZone;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionFiasId() {
        return regionFiasId;
    }

    public void setRegionFiasId(String regionFiasId) {
        this.regionFiasId = regionFiasId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityFiasId() {
        return cityFiasId;
    }

    public void setCityFiasId(String cityFiasId) {
        this.cityFiasId = cityFiasId;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getSettlementFiasId() {
        return settlementFiasId;
    }

    public void setSettlementFiasId(String settlementFiasId) {
        this.settlementFiasId = settlementFiasId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetFiasId() {
        return streetFiasId;
    }

    public void setStreetFiasId(String streetFiasId) {
        this.streetFiasId = streetFiasId;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getHouseFiasId() {
        return houseFiasId;
    }

    public void setHouseFiasId(String houseFiasId) {
        this.houseFiasId = houseFiasId;
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getFiasLevel() {
        return fiasLevel;
    }

    public void setFiasLevel(String fiasLevel) {
        this.fiasLevel = fiasLevel;
    }

    public String getCityKladrId() {
        return cityKladrId;
    }

    public void setCityKladrId(String cityKladrId) {
        this.cityKladrId = cityKladrId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
