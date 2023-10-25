package danielmoisa.union_crm.service

import danielmoisa.union_crm.domain.Job
import danielmoisa.union_crm.model.JobDTO
import danielmoisa.union_crm.repos.JobRepository
import danielmoisa.union_crm.repos.MemberRepository
import danielmoisa.union_crm.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class JobService(
    private val jobRepository: JobRepository,
    private val memberRepository: MemberRepository
) {

    fun findAll(): List<JobDTO> {
        val jobs = jobRepository.findAll(Sort.by("id"))
        return jobs.stream()
                .map { job -> mapToDTO(job, JobDTO()) }
                .toList()
    }

    fun `get`(id: Long): JobDTO = jobRepository.findById(id)
            .map { job -> mapToDTO(job, JobDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(jobDTO: JobDTO): Long {
        val job = Job()
        mapToEntity(jobDTO, job)
        return jobRepository.save(job).id!!
    }

    fun update(id: Long, jobDTO: JobDTO) {
        val job = jobRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(jobDTO, job)
        jobRepository.save(job)
    }

    fun delete(id: Long) {
        jobRepository.deleteById(id)
    }

    private fun mapToDTO(job: Job, jobDTO: JobDTO): JobDTO {
        jobDTO.id = job.id
        jobDTO.position = job.position
        jobDTO.employmentTime = job.employmentTime
        jobDTO.status = job.status
        jobDTO.startDate = job.startDate
        jobDTO.endDate = job.endDate
        jobDTO.memberId = job.memberId?.id
        return jobDTO
    }

    private fun mapToEntity(jobDTO: JobDTO, job: Job): Job {
        job.position = jobDTO.position
        job.employmentTime = jobDTO.employmentTime
        job.status = jobDTO.status
        job.startDate = jobDTO.startDate
        job.endDate = jobDTO.endDate
        val memberId = if (jobDTO.memberId == null) null else
                memberRepository.findById(jobDTO.memberId!!)
                .orElseThrow { NotFoundException("memberId not found") }
        job.memberId = memberId
        return job
    }

}
