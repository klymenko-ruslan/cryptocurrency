package com.outsourcebooster.cryptocurrency.web.rest.office

import com.outsourcebooster.cryptocurrency.web.service.office.OfficeService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("office")
class OfficeController(val officeService: OfficeService) {

    @RequestMapping("/setup")
    fun setUp() = officeService.setUp()

    @RequestMapping("/all")
    fun getOffices() = officeService.findOffices()

    @RequestMapping("/countries")
    fun getCountries() = officeService.findCountryNames()

    @RequestMapping("/cities/{countryName}")
    fun getCities(@PathVariable countryName: String) = officeService.findCityNamesByCountry(countryName)

    @RequestMapping("/get-by-country-city")
    fun getOfficesByCountryCity(@RequestParam("countryName") countryName: String, @RequestParam("cityName") cityName: String) = officeService.getOfficesesByCountryCity(countryName, cityName)
}