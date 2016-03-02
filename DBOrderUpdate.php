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

    if ($_SERVER["REQUEST_METHOD"] == "POST") {

        $item = $_POST['item'];
        $quantity = $_POST['number'];
        $size = $_POST['size'];
        $nameonback = $_POST['NameOnBack'];
        $haspaid = $_POST['haspaid'];
        $paymethod = $_SESSION['paymethod'];
        $stmt = $conn->prepare("SELECT idItems from Items where Item = ?;");
        $stmt->bind_param("s", $item);
        $stmt->execute();
        $id=$_SESSION['ID'];

        $itemid = $stmt->getResult()->fetch_row[0];
        var_dump($item, $quantity, $size, $nameonback, $haspaid, $paymethod, $itemid, $id);
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
<!DOCTYPE html>
<html>
<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <meta char-set="utf-8" />
  <title>Order Input</title>
</head>
<body>
  Please enter in your items one at a time
  <form method="post" id="orderform">
    Item:<br />
    <select name="item">
      <option value="Polo Shirt">Polo Shirt</option>
      <option value="Poolside Shirt">
        Poolside Shirt
      </option>
      <option value="Hoodie">
        Hoodie
      </option>
      <option value="Zippy Hoodie">
        Zippy Hoodie
      </option>
      <option vaue="Hat">
        Hat
      </option>
      <option vale="Shorts">
        Shorts
      </option>
      <option value="Backpack">
        Backpack
      </option>
      <option value="Holdall">
        Holdall
      </option>
    </select><br />
    Quantity:<br />
    <input type="number" name="quantity" min="1" max = "5"/><br />
    Size:<br/>
    <select name="size">
      <option value="9-11"><!--Inside job-->
        Size 9-11
      </option>
      <option value="12-13">
        Size 12-13
      </option>
      <option value="S">
        Small
      </option>
      <option value="M">
        Medium
      </option>
      <option value="L">
        Large
      </option>
      <option value="Bag">
        Bag (Only if your order is a bag)
      </option>
    </select><br />
    Please enter the name/letters to be printed on your item<br />
    <input type="text" name="NameOnBack" /><br />
    Have you paid for this item?<br />
    <input type="radio" name="haspaid" value="1" checked />Yes<br />
    <input type="radio" name="haspaid" value="0" />No<br />
    <input type="submit" value="Submit"/><br/>
  </form>
  <script type="text/javascript">
    function passtophp(e) {
      e.preventDefault()

      var str = $(this).serialize();
      $.ajax('dborderupdate.php', str, function (result) {
        alert(result)
      })

      return false;
    }

    $('#orderform').submit(function (e) {
      e.preventDefault()

      var str = $(this).serialize();

      $.post('dborderupdate.php', str, function (result) {
        alert(result)
      })

      return false;
    })
  </script>
</body>
</html>
