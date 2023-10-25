package danielmoisa.union_crm.repos

import danielmoisa.union_crm.domain.Member
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member, Long>
