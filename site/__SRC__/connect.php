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

class DATABASE_CONNECT
{

  public $connect = array();

  public function __construct()
  {
    // ##### LOCAL

    // $this->connect[0] = "localhost";
    // $this->connect[1] = "easybank";
    // $this->connect[2] = "easybank";
    // $this->connect[3] = "easybank";


    // ##### PROD

    // $this->connect[0] = "easydb";
    // $this->connect[1] = "id20315379_easyuser";
    // $this->connect[2] = "I+m8*yu^yLUHiZ*i";
    // $this->connect[3] = "id20315379_easydb";

    // #### DOCKER

    $this->connect[0] = getenv('MYSQL_HOST');
    $this->connect[1] = getenv('MYSQL_USER');
    $this->connect[2] = getenv('MYSQL_PASSWORD');
    $this->connect[3] = getenv('MYSQL_DATABASE');
  }



  public function __destruct()
  {
    $this->connect[0] = null;
    $this->connect[1] = null;
    $this->connect[2] = null;
    $this->connect[3] = null;
  }
}
