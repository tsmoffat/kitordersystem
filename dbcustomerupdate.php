<?php
session_start();
if (file_exists('kitorder2.xml')) {
    $xml = simplexml_load_file('kitorder2.xml');
    $dbms = $xml->connection->dbms;
    $dbname = $xml->connection->database_name;
    $user_name = $xml->connection->user_name;
    $password = $xml->connection->password;
    $port_numberraw = $xml->connection->port_number;
    $port_number = (int) $port_numberraw;
    $servername = $xml->connection->server_name;
    $conn = new mysqli($servername, $user_name, $password, $dbname, $port_number);
    if ($conn->connect_error){
        die("Connection failed: " . $conn->connect_error);
    }


        $name = test_input($_POST['name']);
        $email = test_input($_POST['email']);
        $squad = test_input($_POST['squad']);

        $paymethod = test_input($_POST['paymethod']);

        $_SESSION["paymethod"]=$paymethod;
        $stmt = $conn->prepare("INSERT INTO Customers (Name, Email_Address, Squad) VALUES (?, ?, ?);");
        $stmt->bind_param("sss", $name, $email, $squad);
        $stmt->execute();

        $sql = $conn->prepare("SELECT MAX(ID) FROM Customers WHERE Name = ?;");
        $sql->bind_param("s", $name);
        $sql->execute();
        $result = $sql->get_result()->fetch_row()[0];
        $_SESSION["ID"] = $result;


} else {
    exit('Failed to open kitorder2.xml.');
}
function test_input($data){
    $data=trim($data);
    $data= stripslashes($data);
    $data=htmlspecialchars($data);
    return $data;
}
?>
