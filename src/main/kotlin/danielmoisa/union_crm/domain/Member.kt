package danielmoisa.union_crm.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.OffsetDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@Table(name = "Members")
@EntityListeners(AuditingEntityListener::class)
class Member {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column
    var dateOfBirth: LocalDate? = null

    @Column
    var description: String? = null

    @Column
    var phoneNumber: String? = null

    @Column
    var address: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid_id")
    var userid: User? = null

    @OneToMany(mappedBy = "memberId")
    var documents: MutableSet<Document>? = null

    @OneToMany(mappedBy = "memberId")
    var jobs: MutableSet<Job>? = null

    @OneToMany(mappedBy = "memberId")
    var bookoffs: MutableSet<Bookoff>? = null

    @CreatedDate
    @Column(
        nullable = false,
        updatable = false
    )
    var dateCreated: OffsetDateTime? = null

    @LastModifiedDate
    @Column(nullable = false)
    var lastUpdated: OffsetDateTime? = null

}
