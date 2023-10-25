package danielmoisa.union_crm.service

import danielmoisa.union_crm.domain.Document
import danielmoisa.union_crm.model.DocumentDTO
import danielmoisa.union_crm.repos.DocumentRepository
import danielmoisa.union_crm.repos.MemberRepository
import danielmoisa.union_crm.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class DocumentService(
    private val documentRepository: DocumentRepository,
    private val memberRepository: MemberRepository
) {

    fun findAll(): List<DocumentDTO> {
        val documents = documentRepository.findAll(Sort.by("id"))
        return documents.stream()
                .map { document -> mapToDTO(document, DocumentDTO()) }
                .toList()
    }

    fun `get`(id: Long): DocumentDTO = documentRepository.findById(id)
            .map { document -> mapToDTO(document, DocumentDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(documentDTO: DocumentDTO): Long {
        val document = Document()
        mapToEntity(documentDTO, document)
        return documentRepository.save(document).id!!
    }

    fun update(id: Long, documentDTO: DocumentDTO) {
        val document = documentRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(documentDTO, document)
        documentRepository.save(document)
    }

    fun delete(id: Long) {
        documentRepository.deleteById(id)
    }

    private fun mapToDTO(document: Document, documentDTO: DocumentDTO): DocumentDTO {
        documentDTO.id = document.id
        documentDTO.name = document.name
        documentDTO.filePath = document.filePath
        documentDTO.memberId = document.memberId?.id
        return documentDTO
    }

    private fun mapToEntity(documentDTO: DocumentDTO, document: Document): Document {
        document.name = documentDTO.name
        document.filePath = documentDTO.filePath
        val memberId = if (documentDTO.memberId == null) null else
                memberRepository.findById(documentDTO.memberId!!)
                .orElseThrow { NotFoundException("memberId not found") }
        document.memberId = memberId
        return document
    }

}
