package danielmoisa.union_crm.repos

import danielmoisa.union_crm.domain.Job
import org.springframework.data.jpa.repository.JpaRepository


interface JobRepository : JpaRepository<Job, Long>
