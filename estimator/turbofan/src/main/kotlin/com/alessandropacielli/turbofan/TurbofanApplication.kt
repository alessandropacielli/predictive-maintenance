package com.alessandropacielli.turbofan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source

@SpringBootApplication
class TurbofanApplication

fun main(args: Array<String>) {
	runApplication<TurbofanApplication>(*args)
}
