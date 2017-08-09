package com.outsourcebooster.cryptocurrency.web.service

import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils
import com.outsourcebooster.cryptocurrency.web.config.security.constant.SecurityRole
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException
import com.outsourcebooster.cryptocurrency.web.repository.UserRepository
import com.outsourcebooster.cryptocurrency.web.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Date
import java.util.stream.Collectors

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Service
open class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder) {

    fun createUser(user: User): User {
        if (userRepository!!.findByEmail(user.email) != null)
            throw NotUniqueEntityException("This email is already in use.")
        user.createdDate = Date()
        user.roles = Arrays.asList(*arrayOf(SecurityRole.USER))
        user.wallet = Wallet(ArrayList<Account>())
        user.userCurrency = UserCurrency.usd
        user.password = passwordEncoder!!.encode(user.password)
        val createdUser = userRepository.save(user)
        Thread {
            ApplicationUtils.sendEmail(ACTIVATION_SUBJECT,
                    ACTIVATION_MESSAGE.replace("\${link}", generateActivationLink(createdUser.password)),
                    createdUser.email)
        }.start()
        return createdUser
    }

    fun activateUser(activationProperty: String) {
        val activatedUser = userRepository!!.findByPassword(activationProperty)
        if (activatedUser != null) {
            activatedUser.isActive = true
            userRepository.save(activatedUser)
        }
    }

    private fun generateActivationLink(decodedPassword: String): String {
        val url = StringBuilder()
        url.append(ApplicationUtils.getCommonProperty("cryptocurrency.web.url"))
                .append(":")
                .append(ApplicationUtils.getCommonProperty("cryptocurrency.web.port"))
                .append("/activate?activationProperty=")
                .append(decodedPassword)
        return url.toString()
    }


    fun updateUserProfile(updatedUser: User): User {
        val savedUser = userRepository!!.findByEmail(updatedUser.email) ?: throw UnsupportedOperationException("There's no updatedUser with such username!")
        savedUser.firstName = updatedUser.firstName
        savedUser.lastName = updatedUser.lastName
        savedUser.profession = updatedUser.profession
        savedUser.email = updatedUser.email
        savedUser.userCurrency = updatedUser.userCurrency
        savedUser.isEnableNotification = updatedUser.isEnableNotification
        if (updatedUser.isEnableNotification) {
            savedUser.notificationRules = null
        }
        userRepository.save(savedUser)
        return savedUser
    }

    fun updateUserNotificationRules(updatedUser: User): User {
        val savedUser = userRepository!!.findByEmail(updatedUser.email) ?: throw UnsupportedOperationException("There's no updatedUser with such username!")
        savedUser.notificationRules = updatedUser.notificationRules
        userRepository.save(savedUser)
        return savedUser
    }

    @Throws(IOException::class)
    fun updateUserImageFile(email: String, file: MultipartFile): User {
        val savedUser = userRepository!!.findByEmail(email) ?: throw UnsupportedOperationException("There's no updatedUser with such username!")

        val pathToUserImages = ApplicationUtils.getCommonProperty("cryptocurrency.web.images.path")
        if (savedUser.imageFileName != null)
            Paths.get(pathToUserImages, savedUser.imageFileName).toFile().delete()
        val newImageFileName = email + System.currentTimeMillis() + file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
        Files.copy(file.inputStream, Paths.get(pathToUserImages, newImageFileName))

        savedUser.imageFileName = newImageFileName
        userRepository.save(savedUser)

        return savedUser
    }

    fun getUserByUsername(email: String): User? {
        return userRepository!!.findByEmail(email)
    }

    fun getUserAuthorities(username: String, password: String): Collection<GrantedAuthority> {
        val user = getUserByUsername(username)
        return if (user == null)
            emptySet<GrantedAuthority>()
        else
            user.roles.map { role ->
                             GrantedAuthority { role }
                           }
    }

    companion object {

        private val ACTIVATION_SUBJECT = "Activation"
        private val ACTIVATION_MESSAGE = "Please follow this link to finish your registration: <a href='\${link}'>Activate</a>"
    }
}