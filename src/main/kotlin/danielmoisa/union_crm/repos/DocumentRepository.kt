package danielmoisa.union_crm.repos

import danielmoisa.union_crm.domain.Document
import org.springframework.data.jpa.repository.JpaRepository


interface DocumentRepository : JpaRepository<Document, Long>
