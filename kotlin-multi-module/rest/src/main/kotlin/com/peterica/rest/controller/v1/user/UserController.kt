package com.peterica.rest.controller.v1.user

import com.peterica.rest.controller.v1.user.request.UserCreateRequest
import com.peterica.rest.controller.v1.user.response.UserResponse
import com.peterica.core.response.ApiResponse
import com.peterica.domain.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{id}")
    fun findUserById(
        @PathVariable("id") id: Long
    ): ApiResponse<UserResponse> {
        val findUser = userService.findById(id)
        return ApiResponse.success(UserResponse.fromDomain(findUser))
    }

    @PostMapping
    fun createUser(
        @RequestBody userCreateRequest: UserCreateRequest
    ): ApiResponse<Boolean> {
        userService.createUser(userCreateRequest.toDomain())
        return ApiResponse.success(true)
    }
}
