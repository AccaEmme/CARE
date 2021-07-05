<?php
@$token = $_GET['token']; // al momento controlla solo che il token non sia nullo

if(!isset($token)){
	include("login.php");
} else {
	include("dashboard.php");
}
?>