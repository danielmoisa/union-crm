package danielmoisa.union_crm.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime


class JobDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var position: String? = null

    @NotNull
    var employmentTime: EmploymentTime? = null

    @NotNull
    var status: Status? = null

    @NotNull
    var startDate: LocalDateTime? = null

    @NotNull
    var endDate: LocalDateTime? = null

    var memberId: Long? = null

}
