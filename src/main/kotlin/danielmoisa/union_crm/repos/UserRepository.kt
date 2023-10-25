package danielmoisa.union_crm.repos

import danielmoisa.union_crm.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

}
