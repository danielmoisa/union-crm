package danielmoisa.union_crm.service

import danielmoisa.union_crm.domain.Member
import danielmoisa.union_crm.model.MemberDTO
import danielmoisa.union_crm.repos.MemberRepository
import danielmoisa.union_crm.repos.UserRepository
import danielmoisa.union_crm.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val userRepository: UserRepository
) {

    fun findAll(): List<MemberDTO> {
        val members = memberRepository.findAll(Sort.by("id"))
        return members.stream()
                .map { member -> mapToDTO(member, MemberDTO()) }
                .toList()
    }

    fun `get`(id: Long): MemberDTO = memberRepository.findById(id)
            .map { member -> mapToDTO(member, MemberDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(memberDTO: MemberDTO): Long {
        val member = Member()
        mapToEntity(memberDTO, member)
        return memberRepository.save(member).id!!
    }

    fun update(id: Long, memberDTO: MemberDTO) {
        val member = memberRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(memberDTO, member)
        memberRepository.save(member)
    }

    fun delete(id: Long) {
        memberRepository.deleteById(id)
    }

    private fun mapToDTO(member: Member, memberDTO: MemberDTO): MemberDTO {
        memberDTO.id = member.id
        memberDTO.name = member.name
        memberDTO.dateOfBirth = member.dateOfBirth
        memberDTO.description = member.description
        memberDTO.phoneNumber = member.phoneNumber
        memberDTO.address = member.address
        memberDTO.userid = member.userid?.id
        return memberDTO
    }

    private fun mapToEntity(memberDTO: MemberDTO, member: Member): Member {
        member.name = memberDTO.name
        member.dateOfBirth = memberDTO.dateOfBirth
        member.description = memberDTO.description
        member.phoneNumber = memberDTO.phoneNumber
        member.address = memberDTO.address
        val userid = if (memberDTO.userid == null) null else
                userRepository.findById(memberDTO.userid!!)
                .orElseThrow { NotFoundException("userid not found") }
        member.userid = userid
        return member
    }

}
