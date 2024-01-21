package com.example.examenib.model.data

import com.example.examenib.model.entities.Usuario
import com.example.examenib.model.entities.Cuenta
import java.time.LocalDate

object BaseDatos {

    private val cuentaAhorro_A = Cuenta(
        "Cuenta de ahorro",
        10000.00,
        LocalDate.of(2015, 7, 10),
        false
    )

    private val cuentaCredito_A = Cuenta(
        "Cuenta de crédito",
        2000.00,
        LocalDate.of(2015, 10, 23),
        true,
    )

    private val cuentaGastos_A = Cuenta(
        "Cuenta-GastosPersonales",
        200.00,
        LocalDate.of(2022, 1, 11),
        false,
    )

    private val cuentaAhorro_B = Cuenta(
        "Cuenta de ahorro",
        14000.00,
        LocalDate.of(2018, 11, 10),
        false
    )

    private val cuentaCredito_B = Cuenta(
        "Cuenta de crédito",
        1500.00,
        LocalDate.of(2020, 11, 23),
        false,
    )

    private val cuentaGastos_B = Cuenta(
        "Cuenta-GastosPersonales",
        650.00,
        LocalDate.of(2023, 6, 14),
        false,
    )

    private val usuarioJenrry = Usuario(
        "Jenrry",
        LocalDate.of(1988, 8, 8),
        2600.02,
        false,
        mutableListOf(cuentaAhorro_A, cuentaCredito_A, cuentaGastos_A)
    )

    private val usuarioRommel = Usuario(
        "Rommel",
        LocalDate.of(2003, 2, 13),
        3600.01,
        false,
        mutableListOf(cuentaAhorro_B, cuentaCredito_B, cuentaGastos_B)
    )

    val datos = listOf(usuarioJenrry, usuarioRommel).toMutableList()

}