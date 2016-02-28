
<?php
// The file test.xml contains an XML document with a root element
// and at least an element /[root]/title.
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
    echo "Connected successfully";
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $name = test_input($_POST["name"]);
        $email = test_input($_POST["email"]);
        $squad = test_input($_POST["squad"]);
        $paymethod = test_input($_POST["paymethod"]);
        echo $name . " " .$email." ".$squad." ".$paymethod;
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
