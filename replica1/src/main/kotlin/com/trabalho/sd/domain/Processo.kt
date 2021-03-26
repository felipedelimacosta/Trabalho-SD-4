package com.trabalho.sd.domain

import java.util.ArrayList


class Processo(var id: Int,
                    var temp: Int,
                    var nome: String,
                    var idLider: Int,

) {
    var isOnline:Boolean = true
    var votos: Int = 0
    var processos = arrayListOf<Processo>()
    constructor(id: Int,temp: Int,nome: String,idLider: Int,isOnline: Boolean):this(id,temp, nome, idLider){
        this.id = id
        this.temp = temp
        this.nome = nome
        this.idLider = idLider
        //this.isOnline = isOnline

    }
}