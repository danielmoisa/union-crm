package danielmoisa.union_crm.rest

import danielmoisa.union_crm.model.BookoffDTO
import danielmoisa.union_crm.service.BookoffService
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
    value = ["/api/bookoffs"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class BookoffResource(
    private val bookoffService: BookoffService
) {

    @GetMapping
    fun getAllBookoffs(): ResponseEntity<List<BookoffDTO>> =
            ResponseEntity.ok(bookoffService.findAll())

    @GetMapping("/{id}")
    fun getBookoff(@PathVariable(name = "id") id: Long): ResponseEntity<BookoffDTO> =
            ResponseEntity.ok(bookoffService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createBookoff(@RequestBody @Valid bookoffDTO: BookoffDTO): ResponseEntity<Long> {
        val createdId = bookoffService.create(bookoffDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateBookoff(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            bookoffDTO: BookoffDTO): ResponseEntity<Long> {
        bookoffService.update(id, bookoffDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteBookoff(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        bookoffService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
