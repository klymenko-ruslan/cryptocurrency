package com.outsourcebooster.cryptocurrency.web.service.user

import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils
import com.outsourcebooster.cryptocurrency.web.config.security.constant.SecurityRole
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException
import com.outsourcebooster.cryptocurrency.web.exception.WrongPasswordException
import com.outsourcebooster.cryptocurrency.web.exception.WrongUsernameException
import com.outsourcebooster.cryptocurrency.web.repository.user.UserRepository
import com.outsourcebooster.cryptocurrency.web.model.account.CryptocurrencyAccount
import com.outsourcebooster.cryptocurrency.web.model.account.FiatAccount
import com.outsourcebooster.cryptocurrency.web.model.user.User
import com.outsourcebooster.cryptocurrency.web.model.user.Wallet
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.ArrayList
import java.util.Arrays
import java.util.Date

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Service
open class UserService(private val userRepository: UserRepository,
                       private val passwordEncoder: PasswordEncoder) {

    fun create(user: User): User {
        if (userRepository.findByEmail(user.email) != null)
            throw NotUniqueEntityException()
        user.id = null
        user.createdDate = Date()
        user.roles = Arrays.asList(*arrayOf(SecurityRole.USER))
        user.wallet = Wallet(ArrayList<CryptocurrencyAccount>(), ArrayList<FiatAccount>())
        user.password = passwordEncoder.encode(user.password)
        val createdUser = userRepository.save(user)
        sentEmailActivationRequest(createdUser.email, createdUser.password)
        createdUser.password = ""
        return createdUser
    }

    fun sentEmailActivationRequest(email: String, password: String) {
        Thread {
            ApplicationUtils.sendEmail(ACTIVATION_SUBJECT,
                    ACTIVATION_MESSAGE.replace("\${link}", generateActivationLink(password, email)),
                    email)
        }.start()
    }

    fun activateUser(email: String, password: String) {
        val user = userRepository.findByEmail(email)
        if(user != null) {
            if(user.password == password) {
                user.isActive = true
                userRepository.save(user)

            }
        }
    }

    private fun generateActivationLink(decodedPassword: String, email: String): String {
        val url = StringBuilder()
        url.append(ApplicationUtils.getCommonProperty("cryptocurrency.web.url"))
                .append(":")
                .append(ApplicationUtils.getCommonProperty("cryptocurrency.web.port"))
                .append("/activate?activationProperty=")
                .append(decodedPassword)
                .append("&email=")
                .append(email)
        return url.toString()
    }


    fun update(updatedUser: User): User {
        val savedUser = userRepository!!.findByEmail(updatedUser.email) ?: throw WrongUsernameException()
        if(passwordEncoder.matches(updatedUser.password, savedUser.password)) {
            savedUser.firstName = updatedUser.firstName
            savedUser.lastName = updatedUser.lastName
            savedUser.profession = updatedUser.profession
            savedUser.email = updatedUser.email
            savedUser.fiatCurrency = updatedUser.fiatCurrency
            savedUser.password = passwordEncoder.encode(updatedUser.newPassword) ?: throw WrongPasswordException()
            val savedUser = userRepository.save(savedUser)
            savedUser.password = ""
            return savedUser
        } else throw WrongPasswordException()



//        savedUser.isEnableNotification = updatedUser.isEnableNotification
//        if (updatedUser.isEnableNotification) {
//            savedUser.notificationRules = null
//        }

    }

    fun updateUserNotificationRules(updatedUser: User): User {
        val savedUser = userRepository!!.findByEmail(updatedUser.email) ?: throw UnsupportedOperationException("There's no updatedUser with such username!")
        savedUser.notificationRules = updatedUser.notificationRules
        userRepository.save(savedUser)
        return savedUser
    }

    @Throws(IOException::class)
    fun updateUserImageFile(email: String, file: MultipartFile): User {
        val savedUser = userRepository.findByEmail(email) ?: throw UnsupportedOperationException("There's no updatedUser with such username!")

        val pathToUserImages = ApplicationUtils.getCommonProperty("cryptocurrency.web.images.path")
        if (savedUser.imageFileName != null)
            Paths.get(pathToUserImages, savedUser.imageFileName).toFile().delete()
        val newImageFileName = email + System.currentTimeMillis() + file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
        Files.copy(file.inputStream, Paths.get(pathToUserImages, newImageFileName))

        savedUser.imageFileName = newImageFileName
        userRepository.save(savedUser)

        return savedUser
    }

    fun getUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun getUserAuthorities(username: String, password: String): Collection<GrantedAuthority> {
        val user = getUserByEmail(username)
        return if (user == null)
            emptySet<GrantedAuthority>()
        else
            user.roles.map { role ->
                             GrantedAuthority { role }
                           }
    }

    companion object {
        private val ACTIVATION_SUBJECT = "Activation"
        private val ACTIVATION_MESSAGE = "Please follow this link to finish your registration: \${link}"
    }
}