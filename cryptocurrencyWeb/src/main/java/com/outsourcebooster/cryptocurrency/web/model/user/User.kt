package com.outsourcebooster.cryptocurrency.web.model.user

import com.outsourcebooster.cryptocurrency.web.model.currency.FiatCurrency
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Document
data class User(@Id var id: String?,
                var createdDate: Date?,
                var newPassword: String?,
                var password: String,
                var firstName: String,
                var lastName: String,
                var profession: String,
                var email: String,
                var isActive: Boolean,
                var fiatCurrency: FiatCurrency?,
                var isEnableNotification: Boolean,
                var imageFileName: String,
                var notificationRules: Collection<NotificationRule>?,
                var roles: MutableCollection<String>,
                var wallet: Wallet?) {
    constructor(): this("",null, null, "","","","","", false, null, false, "", null, mutableListOf(), null)
}
