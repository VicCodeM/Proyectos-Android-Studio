<?php
$servername = "localhost";
$username = "root";
$password = "123456789";
$dbname = "android";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$nombre = $_POST['nombre'];
$email = $_POST['email'];
$edad = $_POST['edad'];

$sql = "INSERT INTO usuarios (nombre, email, edad) VALUES ('$nombre', '$email', '$edad')";

if ($conn->query($sql) === TRUE) {
    echo "Nuevo registro creado exitosamente";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
