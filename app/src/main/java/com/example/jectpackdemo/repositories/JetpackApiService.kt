package com.example.jectpackdemo.repositories

import com.example.jetpack.network.model.BaseRequest
import com.example.jetpack.network.model.BaseResponse
import com.example.jectpackdemo.bean.dto.UserDto
import com.example.jectpackdemo.bean.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @GET("users")
 * suspend fun getUser(): Response<User>
 * Interface methods support kotlin suspend functions which directly return a Response object, creating and asynchronously executing the call while suspending the current function.
 *
 * @GET("users")
 * suspend fun getUser(): User
 * A suspend method may also directly return the body. If a non-2XX status is returned an HttpException will be thrown containing the response.
 */
interface JetpackApiService {

    @POST("enocloud/security/authenticate")
    suspend fun login(@Body request: BaseRequest<LoginRequest>): BaseResponse<String>

    @GET("enocloud/security/user")
    suspend fun getUser(): BaseResponse<UserDto>
}