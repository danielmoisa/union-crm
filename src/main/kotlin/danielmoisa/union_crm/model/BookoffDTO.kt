package danielmoisa.union_crm.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime


class BookoffDTO {

    var id: Long? = null

    @NotNull
    var startDate: LocalDateTime? = null

    @NotNull
    var endDate: LocalDateTime? = null

    @NotNull
    @Size(max = 255)
    var reason: String? = null

    var memberId: Long? = null

}
