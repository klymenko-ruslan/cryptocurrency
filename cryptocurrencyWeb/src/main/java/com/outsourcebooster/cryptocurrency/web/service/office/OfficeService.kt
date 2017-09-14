package com.outsourcebooster.cryptocurrency.web.service.office

import com.outsourcebooster.cryptocurrency.web.model.office.Address
import com.outsourcebooster.cryptocurrency.web.model.office.City
import com.outsourcebooster.cryptocurrency.web.model.office.Country
import com.outsourcebooster.cryptocurrency.web.model.office.Office
import com.outsourcebooster.cryptocurrency.web.repository.office.OfficeRepository
import org.springframework.stereotype.Service

@Service
class OfficeService(val officeRepository: OfficeRepository) {

    fun findOffices() = officeRepository.findAll()

    fun findCountryNames() = officeRepository.findAll()
                                          .map { it.address.city.country.name }
                                          .distinct()

    fun findCityNamesByCountry(countryName: String) = officeRepository.findAll()
                                                                   .filter { it.address.city.country.name == countryName }
                                                                   .map { it.address.city.name }
                                                                   .distinct()

    fun setUp() {
        officeRepository.save(Office(contactNumber = "0636200645", contractPersonName = "Ruslan", skype = "klym", address = Address(street = "Шишковская2", building = "8", appartaments = "1", city =
        City(name ="Харьков", country = Country("Украина")))))
        officeRepository.save(Office(contactNumber = "0636200645", contractPersonName = "Ruslan", skype = "klym", address = Address(street = "Шишковская2", building = "8", appartaments = "1", city =
        City(name ="Киев", country = Country("Украина")))))
        officeRepository.save(Office(contactNumber = "0636200645", contractPersonName = "Ruslan", skype = "klym", address = Address(street = "Шишковская2", building = "8", appartaments = "1", city =
        City(name ="Washington", country = Country("USA")))))
    }

    fun getOfficesesByCountryCity(countryName: String, cityName: String) = officeRepository.findAll()
                                                                                           .filter { it.address.city.country.name == countryName && it.address.city.name == cityName }
}