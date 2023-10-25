package danielmoisa.union_crm.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class DocumentDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var name: String? = null

    @NotNull
    @Size(max = 255)
    var filePath: String? = null

    var memberId: Long? = null

}
