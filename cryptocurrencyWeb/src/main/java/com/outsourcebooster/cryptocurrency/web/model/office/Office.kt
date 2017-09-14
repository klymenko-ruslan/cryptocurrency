package com.outsourcebooster.cryptocurrency.web.model.office

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Office(@Id var id: String? = null,
                  val address: Address,
                  val contractPersonName: String,
                  val contactNumber: String,
                  val skype: String)