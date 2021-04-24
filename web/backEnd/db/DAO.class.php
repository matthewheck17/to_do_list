<?php

abstract class DAO
{
  protected $dbAdapter;

  public function  __construct($dbAdapter)
  {
    if (is_null($dbAdapter) ){
      throw new Exception("Database adapter is null");
    }
    $this->dbAdapter = $dbAdapter;
  }

  // The select statement for the table
  abstract protected function getSelectStatement();

  abstract protected function getSpecificSelectStatement();

  abstract protected function getInsertStatement();

  abstract protected function getUpdateStatement();

  abstract protected function getDeleteStatement();
  
  public function findAll() {
    $sql = $this->getSelectStatement();
    $resultArr = $this->dbAdapter->fetchAsArray($sql);
    return $resultArr;
  }

  public function findByID($id) {
    $sql = $this->getSpecificSelectStatement();
    $resultArr = $this->dbAdapter->fetchAsArrayByID($sql, $id);
    return $resultArr;
  }

  public function insert($object) {
    $sql = $this->getInsertStatement();
    return $this->dbAdapter->runQueryWithParamsGetLastInsertID($sql, $object->toArray(), $object->getTypes()); //returns id of most recent insert
  }

  public function update($object) {
    $sql = $this->getUpdateStatement();
    $this->dbAdapter->runQueryWithParams($sql, $object->toArrayIDLast(), $object->getTypes());
  }

  public function delete($id) {
    $sql = $this->getDeleteStatement();
    $this->dbAdapter->runQueryWithID($sql, $id);
  }
}