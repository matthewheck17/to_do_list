<?php

  interface DatabaseAdapterInterface
  {
    function setConnectionInfo($values=array());
    function runQuery($sql);
    function fetchAsArray($sql);
  }

?>