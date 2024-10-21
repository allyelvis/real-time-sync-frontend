package com.example.realtime

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import kotlin.concurrent.thread

fun main() {
    val client = OkHttpClient()
    val request = Request.Builder().url("ws://localhost:8080/ws-invoice").build()

    val listener = object : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            println("Received message: $text")
        }
    }

    val webSocket = client.newWebSocket(request, listener)

    // Send message to WebSocket
    thread {
        webSocket.send("New Invoice Update")
    }
}
