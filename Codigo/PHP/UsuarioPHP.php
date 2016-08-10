<?php

$usu=$_REQUEST['usuario'];

$cnx= new PDO("mysql:host=10.0.1.3;dbname=mydb","root","12");

$res=$cnx->query("select * from Usuario where Nombre ='$usu'");
$datos=array();
foreach ($res as $fila){
    $datos[]=$fila;
}
echo json_encode($datos);