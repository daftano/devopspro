<?php

$request_body = file_get_contents('php://input');
$data = json_decode($request_body);

if (!empty($data->name) && !empty($data->password)) {
    header("HTTP/1.1 200 OK");
    exit;
}

header("HTTP/1.1 401 Unauthorized");
exit;

?>
