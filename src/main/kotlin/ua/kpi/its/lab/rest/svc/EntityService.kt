package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.SoftwareProductRequest
import ua.kpi.its.lab.rest.dto.SoftwareProductResponse

interface SoftwareProductService {
    fun create(softwareProduct: SoftwareProductRequest): SoftwareProductResponse

    fun read(): List<SoftwareProductResponse>

    fun readById(id: Long): SoftwareProductResponse

    fun updateById(id: Long, softwareProduct: SoftwareProductRequest): SoftwareProductResponse

    fun deleteById(id: Long): SoftwareProductResponse
}
