<?php 

abstract class Obj {

    public function toArray() {
        $array = (array) $this;
        $outputArr = [];
        foreach ($array as $value) {
            if ($value){ //as long as value isn't null
                array_push($outputArr, $value);
            }
        }
        return $outputArr;
    }

    abstract function toArrayIDLast();

    public function getTypes() {
        $array = (array) $this;
        $types = "";
        foreach ($array as $value) {
            $type = gettype($value);
            switch ($type){
                case "boolean":
                    $types = $types . "b";
                    break;
                case "integer":
                    $types = $types . "i";
                    break;
                case "string":
                    $types = $types . "s";
                    break;
                default:
                    break;
            }
        }
        return $types;
    }
}