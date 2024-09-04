<?php
// Parámetros de la conexión
$host = 'localhost';
$usuario = 'root';
$contraseña = '';
$base_datos = 'tienda';

// Crear la conexión
$conexion = new mysqli($host, $usuario, $contraseña, $base_datos);
$conexion->set_charset("utf8");

// Verificar la conexión
if ($conexion->connect_error) {
    die('Error de conexión: ' . $conexion->connect_error);
}
?>