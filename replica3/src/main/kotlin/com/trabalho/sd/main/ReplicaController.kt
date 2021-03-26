package com.trabalho.sd.main

import com.trabalho.sd.utilidades.Util
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/replica")
class ReplicaController(private var utilidades: Util) {


    @PostMapping("/reciver1")
    fun reciver1(@RequestBody mensagem: String){
        println(mensagem)
        //utilidades.caiu()
        //utilidades.trocaLider()

    }

    @PostMapping("/reciver2")
    fun reciver2(@RequestBody mensagem: String){
        println(mensagem)
       // utilidades.caiu()
       // utilidades.trocaLider()

    }
    @KafkaListener(
        topics = ["kafka-trabalho-sd-replica3-mensagem"],
        groupId = "kafka-trabalho-group"
    )
    @PostMapping("/reciver3")
    fun reciver3(@RequestBody mensagem: String){
        println(mensagem)
        // utilidades.caiu()
        // utilidades.trocaLider()

    }

    @PostMapping("/isAlive")
    fun isAlive(@RequestBody mensagem: String): String {
        if(utilidades.processo.isOnline.equals(false)) {
            return "false"
        }
        return "true"
        // utilidades.caiu()
        // utilidades.trocaLider()
    }

    @PostMapping("/votacao")
    fun votacao(@RequestBody mensagem: String){
        println(mensagem)
        utilidades.processo.votos = utilidades.processo.votos +1
        println("Votos Computados" + utilidades.processo.votos)

    }
    @PostMapping("/eleicao")
    fun eleicao(@RequestBody mensagem: String){
        println(mensagem)

    }
}