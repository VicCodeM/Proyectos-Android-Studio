<?php
include("conexion.php");

$codigo = $_POST['codigo'];
$producto = $_POST['producto'];
$precio = $_POST['precio'];
$fabricante = $_POST['fabricante'];

$consulta = "UPDATE producto SET producto ='".$producto."', precio = '".$precio."', fabricante = '".$fabricante."' WHERE codigo = '".$codigo."'";

if (mysqli_query($conexion, $consulta)) {
    echo "Producto actualizado correctamente";
} else {
    echo "Error al actualizar producto: ". mysqli_error($conexion);
}
?>