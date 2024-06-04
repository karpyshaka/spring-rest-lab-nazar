package ua.kpi.its.lab.rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.SoftwareProductRequest
import ua.kpi.its.lab.rest.dto.SoftwareProductResponse
import ua.kpi.its.lab.rest.svc.SoftwareProductService

@RestController
@RequestMapping("/software-products")
class SoftwareProductController @Autowired constructor(
    private val softwareProductService: SoftwareProductService
) {
    @GetMapping(path = ["", "/"])
    fun softwareProducts(): List<SoftwareProductResponse> = softwareProductService.read()

    @GetMapping("{id}")
    fun readSoftwareProduct(@PathVariable("id") id: Long): ResponseEntity<SoftwareProductResponse> {
        return wrapNotFound { softwareProductService.readById(id) }
    }

    @PostMapping(path = ["", "/"])
    fun createSoftwareProduct(@RequestBody softwareProduct: SoftwareProductRequest): SoftwareProductResponse {
        return softwareProductService.create(softwareProduct)
    }

    @PutMapping("{id}")
    fun updateSoftwareProduct(
        @PathVariable("id") id: Long,
        @RequestBody softwareProduct: SoftwareProductRequest
    ): ResponseEntity<SoftwareProductResponse> {
        return wrapNotFound { softwareProductService.updateById(id, softwareProduct) }
    }

    @DeleteMapping("{id}")
    fun deleteSoftwareProduct(@PathVariable("id") id: Long): ResponseEntity<SoftwareProductResponse> {
        return wrapNotFound { softwareProductService.deleteById(id) }
    }

    fun <T> wrapNotFound(call: () -> T): ResponseEntity<T> {
        return try {
            val result = call()
            ResponseEntity.ok(result)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }
    }
}
