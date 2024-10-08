package com.neocampunism.service.professor

import com.neocampunism.model.Professor
import com.neocampunism.repository.professor.ProfessorRepository
import com.neocampunism.response.ApiResponse

class ProfessorServiceImpl(private val professorRepository: ProfessorRepository) : ProfessorService {
    override suspend fun createProfessor(professor: Professor): ApiResponse<Professor> {
        val newProfessor = professorRepository.addProfessor(professor)
            ?: return ApiResponse(status = "failed", message = "Failed to create professor")

        return ApiResponse(status = "success", message = "Professor created", data = newProfessor)
    }

    override suspend fun getProfessors(): ApiResponse<Map<String, List<Professor>>> {
        val professors = professorRepository.getAllProfessors()
        return ApiResponse(
            status = "success",
            message = "Professors retrieved",
            data = mapOf("professors" to professors)
        )
    }

    override suspend fun getProfessor(id: Int): ApiResponse<Professor> {
        val professor = professorRepository.getProfessorById(id)
            ?: return ApiResponse(status = "failed", message = "Professor not found")

        return ApiResponse(status = "success", message = "Professor retrieved", data = professor)
    }

    override suspend fun updateProfessor(id: Int, professor: Professor): ApiResponse<Professor> {
        val updatedProfessor = professorRepository.updateProfessor(id, professor)
            ?: return ApiResponse(status = "failed", message = "Failed to update professor")

        return ApiResponse(
            status = "success",
            message = "Professor updated",
            data = updatedProfessor
        )
    }

    override suspend fun deleteProfessor(id: Int): ApiResponse<Professor> {
        return if (professorRepository.deleteProfessor(id)) {
            ApiResponse(status = "success", message = "Professor deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete professor")
        }
    }
}
