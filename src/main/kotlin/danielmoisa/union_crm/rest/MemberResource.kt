package danielmoisa.union_crm.rest

import danielmoisa.union_crm.model.MemberDTO
import danielmoisa.union_crm.service.MemberService
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
    value = ["/api/members"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class MemberResource(
    private val memberService: MemberService
) {

    @GetMapping
    fun getAllMembers(): ResponseEntity<List<MemberDTO>> =
            ResponseEntity.ok(memberService.findAll())

    @GetMapping("/{id}")
    fun getMember(@PathVariable(name = "id") id: Long): ResponseEntity<MemberDTO> =
            ResponseEntity.ok(memberService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createMember(@RequestBody @Valid memberDTO: MemberDTO): ResponseEntity<Long> {
        val createdId = memberService.create(memberDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateMember(@PathVariable(name = "id") id: Long, @RequestBody @Valid memberDTO: MemberDTO):
            ResponseEntity<Long> {
        memberService.update(id, memberDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteMember(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        memberService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
