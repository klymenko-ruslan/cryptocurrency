package com.outsourcebooster.cryptocurrency.web.model

import org.springframework.data.annotation.Id
import java.util.Date

/**
 * Created by rklimemnko on 29.05.2016.
 */
data class User(@Id var id: String,
                var createdDate: Date?,
                var password: String,
                var firstName: String,
                var lastName: String,
                var profession: String,
                var email: String,
                var isActive: Boolean,
                var userCurrency: UserCurrency?,
                var isEnableNotification: Boolean,
                var imageFileName: String,
                var notificationRules: Collection<NotificationRule>?,
                var roles: MutableCollection<String>,
                var wallet: Wallet?) {
    constructor(): this("",null, "","","","","", false, null, false, "", null, mutableListOf(), null)
}
