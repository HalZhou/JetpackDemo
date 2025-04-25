package com.example.jectpackdemo.base

import com.example.jetpack.common.utils.ToastUtils
import java.net.ConnectException
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun tryCatchFromResponse(block: suspend () -> Unit, onFailure: (String?, String?) -> Unit) {
    try {
        block.invoke()
    } catch (e: SocketTimeoutException) {
        ToastUtils.show("网络连接超时")
        onFailure.invoke("", e.message)
    } catch (e: UnknownHostException) {
        ToastUtils.show("网络有问题，请检查网络")
        onFailure.invoke("", e.message)
    } catch (e: ConnectException) {
        ToastUtils.show("网络有问题，请检查网络")
        onFailure.invoke("", e.message)
    } catch (e: MalformedURLException) {
        ToastUtils.show("网络有问题，请检查网络")
        onFailure.invoke("", e.message)
    } catch (e: Exception) {
        ToastUtils.show("${e.message}")
        onFailure.invoke("", e.message)
    }
}

