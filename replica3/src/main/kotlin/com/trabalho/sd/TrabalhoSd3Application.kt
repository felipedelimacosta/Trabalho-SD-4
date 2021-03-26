package com.trabalho.sd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TrabalhoSd3Application

fun main(args: Array<String>) {
    runApplication<TrabalhoSd3Application>(*args)
}
