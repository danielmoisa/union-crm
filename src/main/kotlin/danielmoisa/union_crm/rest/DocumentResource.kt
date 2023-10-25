package danielmoisa.union_crm.rest

import danielmoisa.union_crm.model.DocumentDTO
import danielmoisa.union_crm.service.DocumentService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/documents"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class DocumentResource(
    private val documentService: DocumentService
) {

    @GetMapping
    fun getAllDocuments(): ResponseEntity<List<DocumentDTO>> =
            ResponseEntity.ok(documentService.findAll())

    @GetMapping("/{id}")
    fun getDocument(@PathVariable(name = "id") id: Long): ResponseEntity<DocumentDTO> =
            ResponseEntity.ok(documentService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createDocument(@RequestBody @Valid documentDTO: DocumentDTO): ResponseEntity<Long> {
        val createdId = documentService.create(documentDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateDocument(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            documentDTO: DocumentDTO): ResponseEntity<Long> {
        documentService.update(id, documentDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteDocument(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        documentService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
