package danielmoisa.union_crm.service

import danielmoisa.union_crm.domain.Bookoff
import danielmoisa.union_crm.model.BookoffDTO
import danielmoisa.union_crm.repos.BookoffRepository
import danielmoisa.union_crm.repos.MemberRepository
import danielmoisa.union_crm.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class BookoffService(
    private val bookoffRepository: BookoffRepository,
    private val memberRepository: MemberRepository
) {

    fun findAll(): List<BookoffDTO> {
        val bookoffs = bookoffRepository.findAll(Sort.by("id"))
        return bookoffs.stream()
                .map { bookoff -> mapToDTO(bookoff, BookoffDTO()) }
                .toList()
    }

    fun `get`(id: Long): BookoffDTO = bookoffRepository.findById(id)
            .map { bookoff -> mapToDTO(bookoff, BookoffDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(bookoffDTO: BookoffDTO): Long {
        val bookoff = Bookoff()
        mapToEntity(bookoffDTO, bookoff)
        return bookoffRepository.save(bookoff).id!!
    }

    fun update(id: Long, bookoffDTO: BookoffDTO) {
        val bookoff = bookoffRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(bookoffDTO, bookoff)
        bookoffRepository.save(bookoff)
    }

    fun delete(id: Long) {
        bookoffRepository.deleteById(id)
    }

    private fun mapToDTO(bookoff: Bookoff, bookoffDTO: BookoffDTO): BookoffDTO {
        bookoffDTO.id = bookoff.id
        bookoffDTO.startDate = bookoff.startDate
        bookoffDTO.endDate = bookoff.endDate
        bookoffDTO.reason = bookoff.reason
        bookoffDTO.memberId = bookoff.memberId?.id
        return bookoffDTO
    }

    private fun mapToEntity(bookoffDTO: BookoffDTO, bookoff: Bookoff): Bookoff {
        bookoff.startDate = bookoffDTO.startDate
        bookoff.endDate = bookoffDTO.endDate
        bookoff.reason = bookoffDTO.reason
        val memberId = if (bookoffDTO.memberId == null) null else
                memberRepository.findById(bookoffDTO.memberId!!)
                .orElseThrow { NotFoundException("memberId not found") }
        bookoff.memberId = memberId
        return bookoff
    }

}
