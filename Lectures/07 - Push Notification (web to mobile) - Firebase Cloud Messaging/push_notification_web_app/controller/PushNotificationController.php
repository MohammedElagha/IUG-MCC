<?php

include_once ('RegistrationIdController.php');

class PushNotificationController {

	private function push_notification_to_group ($registration_ids, $title, $message) {

		$api_access_key = 'AAAAoIbB19k:APA91bGtU4gJyrH89LWyHh8bYC5KkoD2mqO_iX1yZ8Oc2NL3CBqWsHm4ENk2m6LDVrpAd8Bz2QB22QyLK44ZqOLDMVF8KYaUJcm_LTxDlNfkhCQy21cvKM37DJcRiOqXULsVPBl9by_J';
    	$fcmUrl = 'https://fcm.googleapis.com/fcm/send';

    	$notification = [
            'title' => $title,
            'body' => $message
        ];

        $extraNotificationData = ["message" => $notification];

        $fcmNotification = [
            'registration_ids' => $registration_ids,
            'notification' => $notification,
            'data' => $extraNotificationData
        ];

        $headers = [
            'Authorization: key=' . $api_access_key,
            'Content-Type: application/json'
        ];


        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL,$fcmUrl);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fcmNotification));
        $result = curl_exec($ch);

        // var_dump($result); die();

        curl_close($ch);
	}

	public function push_notification ($title, $message) {
		$registration_id_controller = new RegistrationIdController;
		$client_registration_ids = $registration_id_controller->get_all();
        $client_registration_ids_no = count($client_registration_ids);

		$max_no_of_ids = 1000;
		$start = 0;
		$finish = ($max_no_of_ids < $client_registration_ids_no) ? $max_no_of_ids-1 : $client_registration_ids_no;

		$parts = ceil($client_registration_ids_no / $max_no_of_ids);

		for ($i = 0 ; $i < $parts ; $i++) {
			$registration_ids_part = array();

			for ($j = $start ; $j < $finish ; $j++) {
				$registration_id = $client_registration_ids[$j]->registration_id;
				array_push($registration_ids_part, $registration_id);
			}

			$this->push_notification_to_group($registration_ids_part, $title, $message);

			$start = $finish + 1;
            $client_registration_ids_no -= $max_no_of_ids;
			$finish = $finish + (($max_no_of_ids < $client_registration_ids_no) ? $max_no_of_ids-1 : $client_registration_ids_no);

			sleep(1);
		}
	}
}

?>