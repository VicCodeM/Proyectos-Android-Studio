<?php
include("conexion.php");

$codigo=$_GET['codigo'];

$consulta = "SELECT * FROM producto where codigo = '$codigo'";

$resultado = $conexion -> query($consulta);

if (!$resultado) {
    echo "Error en la consulta SQL: ". $conexion -> error;
    exit;
}

$producto = array();

while($fila=$resultado ->fetch_array()){
    $producto[] = array_map('utf8_encode', $fila);
}

// Convertir el array $producto en un string JSON
$json_producto = json_encode($producto);

// Imprimir el string JSON
echo $json_producto;

$resultado -> close();
?>