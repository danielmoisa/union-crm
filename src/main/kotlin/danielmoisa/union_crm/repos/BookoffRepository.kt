package danielmoisa.union_crm.repos

import danielmoisa.union_crm.domain.Bookoff
import org.springframework.data.jpa.repository.JpaRepository


interface BookoffRepository : JpaRepository<Bookoff, Long>
