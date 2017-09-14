package com.outsourcebooster.cryptocurrency.web.repository.office

import com.outsourcebooster.cryptocurrency.web.model.office.Office
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OfficeRepository : MongoRepository<Office, String>