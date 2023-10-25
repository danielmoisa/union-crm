package danielmoisa.union_crm.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate


class MemberDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    var dateOfBirth: LocalDate? = null

    @Size(max = 255)
    var description: String? = null

    @Size(max = 255)
    var phoneNumber: String? = null

    @Size(max = 255)
    var address: String? = null

    var userid: Long? = null

}
