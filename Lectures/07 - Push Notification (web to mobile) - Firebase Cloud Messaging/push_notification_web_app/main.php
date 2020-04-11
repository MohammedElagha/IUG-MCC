<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<form method="post">
		title: <input type="text" name="title"> <br><br>
		body: <input type="text" name="body"> <br><br>
		<button type="submit">SEND</button>
	</form>
</body>
</html>

<?php

// include_once ('database/connection.php');
// include_once ('model/ClientRegistrationId.php');
include_once ('controller/PushNotificationController.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	$title = $_POST['title'];
	$body = $_POST['body'];

	$pusher = new PushNotificationController;
	$pusher->push_notification($title, $body);
}

?>