<?php

    $id=$_REQUEST['id'];
    $apellido=$_REQUEST['apellido'];
    $cedula=$_REQUEST['cedula'];
    $correo=$_REQUEST['correo'];
    $direccion=$_REQUEST['direccion'];
    $fechaNacimiento=$_REQUEST['fechaNacimiento'];
    $genero=$_REQUEST['genero'];
    $nombre=$_REQUEST['nombre'];
    $rol=$_REQUEST['rol'];
    $tipoSangre=$_REQUEST['tipoSangre'];
    $eps=$_REQUEST['eps'];

    $cnx= new PDO("mysql:host=localhost;dbname=mydb","root","12");

    $cnx->query("INSERT INTO Usuario (`idUsuario`, `apellido`, `cedula`, `correo`, `direccion`, `fechaNacimiento`, `foto`, `genero`, `nombre`, `rol`, `tipoSangre`, `eps`)"
                               . " VALUES ('$id', '$apellido', '$cedula', '$correo', '$direccion', '$fechaNacimiento','$genero', '$nombre', '$rol', '$tipoSangre', '$eps')");
    
?>