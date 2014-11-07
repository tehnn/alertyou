<?php
    $conn = mysql_connect('192.168.198.15','tehnn','112233');
    mysql_select_db("gcm");
    
    $user= $_GET['user'];
    $regID = $_GET['regID'];
    //echo $regID;
    
   // $sql = " insert into user ('id','user','keys') values ('','$user','$regID') ";
    $dateadd = date('Y-m-d H:i:s');
    $sql = " INSERT INTO `user` (`username`, `keyname` ,`dateadd`) VALUES ('$user', '$regID','$dateadd') ";
    
    if(mysql_query($sql)){
        echo strtolower(trim("OK!!"));
    }else{
        echo "You already registed.";
    }
    
?>

