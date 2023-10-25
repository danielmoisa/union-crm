package danielmoisa.union_crm.service

import danielmoisa.union_crm.domain.User
import danielmoisa.union_crm.model.UserDTO
import danielmoisa.union_crm.repos.UserRepository
import danielmoisa.union_crm.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<UserDTO> {
        val users = userRepository.findAll(Sort.by("id"))
        return users.stream()
                .map { user -> mapToDTO(user, UserDTO()) }
                .toList()
    }

    fun `get`(id: Long): UserDTO = userRepository.findById(id)
            .map { user -> mapToDTO(user, UserDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(userDTO: UserDTO): Long {
        val user = User()
        mapToEntity(userDTO, user)
        return userRepository.save(user).id!!
    }

    fun update(id: Long, userDTO: UserDTO) {
        val user = userRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(userDTO, user)
        userRepository.save(user)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    private fun mapToDTO(user: User, userDTO: UserDTO): UserDTO {
        userDTO.id = user.id
        userDTO.username = user.username
        userDTO.email = user.email
        userDTO.password = user.password
        userDTO.profilePicture = user.profilePicture
        return userDTO
    }

    private fun mapToEntity(userDTO: UserDTO, user: User): User {
        user.username = userDTO.username
        user.email = userDTO.email
        user.password = userDTO.password
        user.profilePicture = userDTO.profilePicture
        return user
    }

    fun emailExists(email: String?): Boolean = userRepository.existsByEmailIgnoreCase(email)

}
