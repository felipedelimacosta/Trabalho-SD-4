package com.trabalho.sd.utilidades

import com.trabalho.sd.domain.Processo
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class Util (
    private val restTemplate: RestTemplate,
    private val kafkaTemplate: KafkaTemplate<String,String>

) {
    var processoLider  = Processo(10,1,"cache do lider", 1, true)

    var processo  = Processo(2,1,"replica 1", 1, true)
    var processo1 = Processo(1,1,"replica 2", 1, true)
    var processo3 = Processo(3,1,"replica 3", 1, true)



    //@Scheduled(fixedDelay = 5000, initialDelay = 10000)
    fun mensagem (){
        var mensagem = "Tó mandando menssagem do lider: " + processo.id
        kafkaTemplate.send("kafka-trabalho-sd-replica1-mensagem", mensagem)
        kafkaTemplate.send("kafka-trabalho-sd-replica3-mensagem", mensagem)

        //restTemplate.postForEntity("http://localhost:8080/replica/reciver"+ processo.idLider,mensagem, String.javaClass)


        //("http://localhost:8080/replica/recive1","Tó mandando menssagem para o lider: " +processo.idLider )
    }

    // @Scheduled(fixedDelay = 5200, initialDelay = 11000)
    fun trocaLider (){
        if(this.processo.isOnline) {
            processo.idLider = processoLider.idLider +1
            processoLider.idLider = this.processo.id

            processo.isOnline = true
            processoLider.isOnline = true


        }
        //processo.idLider  = if (processo.idLider > 3)  0 else processo.idLider+1

    }
    //@Scheduled(fixedDelay = 5100, initialDelay = 11000)
    fun liderCaiu (){
        processoLider.isOnline = false
        println("processo lider caiu " + processoLider.isOnline)
        //processo.idLider  = if (processo.idLider > 3)  0 else processo.idLider+1

    }

    //@Scheduled(fixedDelay = 5100, initialDelay = 12000)
    fun caiu (){
        if(!processoLider.isOnline) {
            //processo.isOnline = false
            processoLider.isOnline = false
        }
    }

    //@Scheduled(fixedDelay = 5100, initialDelay = 13000)
    fun verificarOn (){
        if(!processoLider.isOnline){
            this.eleicao()
        }
    }

    //@Scheduled(fixedDelay = 5100, initialDelay = 11000)
    fun eleicao (){
        println("CHEGUEI NA ELEICAO")
        //Lembrar que a replica que caiu, não vota.
        if(processo.isOnline) {
            processo.votos = processo.votos + 1
            restTemplate.postForEntity(
                "http://localhost:8081/replica/votacao",
                "Mensagem de votação do processo: " + processo1.id, String.javaClass
            )
            restTemplate.postForEntity(
                "http://localhost:8081/replica/votacao",
                "Mensagem de votação do processo: " + processo3.id, String.javaClass
            )

        }

        this.trocaLider()

        processo.votos = 0



    }


}