package danielmoisa.union_crm.util

import java.lang.RuntimeException


class NotFoundException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

}
