<?php


include_once ($_SERVER['DOCUMENT_ROOT'] . '/push_notification_lec/model/ClientRegistrationId.php');

class RegistrationIdController {

	public function get_all () {
		include_once ($_SERVER['DOCUMENT_ROOT'] . '/push_notification_lec/database/connection.php');

		$query = 'select * from client_registration_ids';
		$result = mysqli_query($connection, $query);

		$client_registration_ids = array();

		if ($result) {
			if (mysqli_num_rows($result)) {
				while ($row = $result->fetch_assoc()) {
					$client_registration_id = new ClientRegistrationId();
					$client_registration_id->id = $row['id'];
					$client_registration_id->client_id = $row['client_id'];
					$client_registration_id->registration_id = $row['registration_id'];
					array_push($client_registration_ids, $client_registration_id);
				}
			}
		}

		return $client_registration_ids;
	}

}


?>