<?php

/*
 * Copyright (c) 2018 Barchampas Gerasimos <makindosx@gmail.com>
 * online-banking a online banking system for local businesses.
 *
 * online-banking is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 *
 * online-banking is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License, version 3,
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */
require_once('__SRC__/connect.php');

if (class_exists('DATABASE_CONNECT')) {

  $obj_conn  = new DATABASE_CONNECT;

  $host = $obj_conn->connect[0];
  $user = $obj_conn->connect[1];
  $pass = $obj_conn->connect[2];
  $db   = $obj_conn->connect[3];


  $conn = new mysqli($host, $user, $pass, $db);

  if ($conn->connect_error) {
    die("Cannot connect " . $conn->connect_error);
  } else {

    $sqlDrop = "DROP TABLE `accounts`, `customers`, `easybank_all_reserves`, `easybank_reserve_currency`, `notifications`, `orders`, `transactions_all`, `transactions_anyone_bank`, `transactions_easy_bank`;";

    $sqlFile = "sql/easysqlseed.sql";

    $result1 = $conn->query($sqlDrop);
    echo $result1;

    $sql = file_get_contents($sqlFile);

    if ($conn->multi_query($sql) === TRUE) {
      echo "Tables created successfully!";
    } else {
      echo "Error creating tables: " . $conn->error;
    }
  }
  $conn->close();
}
