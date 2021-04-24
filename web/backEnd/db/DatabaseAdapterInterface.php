<?php

  interface DatabaseAdapterInterface
  {
    function setConnectionInfo($values=array());
    function runQuery($sql);
    function runQueryWithID($sql, $id);
    function runQueryWithParams($sql, $params, $types);
    function runQueryWithParamsGetLastInsertID($sql, $params, $types);
    function fetchAsArray($sql);
    function fetchAsArrayByID($sql, $id);
  }

?>