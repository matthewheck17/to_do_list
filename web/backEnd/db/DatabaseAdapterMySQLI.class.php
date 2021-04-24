<?php

require_once("DatabaseAdapterInterface.php");


class DatabaseAdapterMySQLI implements DatabaseAdapterInterface
{
  private $mysqli;

  public function  __construct($values) {
    $this->setConnectionInfo($values);
  }

  function setConnectionInfo($values=array()) {
    $host = $values[0];
    $user = $values[1];
    $password = $values[2];
    $name = $values[3];
    $mysqli = new mysqli($host,$user,$password, $name);
    $this->mysqli = $mysqli;
  }
  

  public function runQuery($sql) {
    $result = $this->mysqli->query($sql);
    return $result;
  }

  public function runQueryWithID($sql, $id) {

    $stmt = $this->mysqli->prepare($sql);
    $stmt->bind_param("i", $id);
    $stmt->execute();
    $result = $stmt->get_result();
    return $result;
  }

  public function runQueryWithParams($sql, $params, $types) {
    $stmt = $this->mysqli->prepare($sql);
    $stmt->bind_param($types, ...$params);
    $stmt->execute();
  }

  public function runQueryWithParamsGetLastInsertID($sql, $params, $types) {
    $stmt = $this->mysqli->prepare($sql);
    $stmt->bind_param($types, ...$params);
    $stmt->execute();
    $id = mysqli_insert_id($this->mysqli); //get id of most recent insert
    return $id;
  }

  public function fetchAsArray($sql) {
    $result = $this->mysqli->query($sql);
    $resultArr = array();
    while ($row = $result->fetch_assoc()){
      array_push($resultArr, $row);
    }
    return $resultArr;
  }

  public function fetchAsArrayByID($sql, $id) {
    $stmt = $this->mysqli->prepare($sql);
    $stmt->bind_param("i", $id);
    $stmt->execute();
    $result = $stmt->get_result();
    $resultArr = array();
    while ($row = $result->fetch_assoc()){
      array_push($resultArr, $row);
    }
    return $resultArr;
  }
}
