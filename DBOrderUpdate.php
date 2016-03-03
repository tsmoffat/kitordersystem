<?php
// The file test.xml contains an XML document with a root element
// and at least an element /[root]/title.
session_start();
error_reporting(E_ALL);
ini_set('display_errors', 1);
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

    if ($_SERVER["REQUEST_METHOD"] == "POST") {

        $item = $_POST['item'];
        $quantityraw = $_POST['quantity'];
        $quantity = $quantityraw;
        $size = $_POST['size'];
        $nameonback = $_POST['NameOnBack'];
        $haspaidraw = $_POST['haspaid'];
        $haspaid = $haspaidraw;
        $paymethod = $_SESSION['paymethod'];
        $stmt = $conn->prepare("SELECT idItems from Items where Item = ?;");
        $stmt->bind_param("s", $item);
        $stmt->execute();
        $idraw=$_SESSION['ID'];
        $id = $idraw;
        $itemidraw = $stmt->get_result()->fetch_row()[0];
        $itemid = $itemidraw;
        var_dump($item, $quantity, $size, $nameonback, $haspaid, $paymethod, $itemid, $id);
        $stmt = $conn->prepare("INSERT INTO Orders (`Orders`, `OrderSize`, `OrderNumber`, `CustomerID`, `NameOnGarment`, `PaidFor`, `PaymentMethod`) values (?,?,?,?,?,?,?);");
        $stmt->bind_param('isiisis', $itemid, $size, $quantity, $id, $nameonback, $haspaid, $paymethod);
        $stmt->execute();

    }

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
