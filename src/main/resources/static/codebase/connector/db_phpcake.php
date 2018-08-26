<?php
/*
	@author dhtmlx.com
	@license GPL, see license.txt
*/
require_once("db_common.php");

//DataProcessor::$action_param ="dhx_editor_status";

/*! Implementation of DataWrapper for PDO

if you plan to use it for Oracle - use Oracle connection type instead
**/

class PHPCakeDBDataWrapper extends ArrayDBDataWrapper
{

    public function select($source)
    {
        $sourceData = $source->get_source();
        if (is_array($sourceData))    //result of find
            $query = $sourceData;
        else
            $query = $sourceData->find("all");

        $temp = array();
        foreach ($query as $row)
            $temp[] = $row->toArray();

        return new ArrayQueryWrapper($temp);
    }

    public function insert($data, $source)
    {
        $sourceData = $source->get_source();
        $obj = $sourceData->newEntity();
        $obj = $this->fillModel($obj, $data);
        $savedResult = $source->get_source()->save($obj);
        $data->success($savedResult->get($this->config->id["db_name"]));
    }

    private function fillModel($obj, $data)
    {
        //Map data to model object.
        for ($i = 0; $i < count($this->config->text); $i++) {
            $step = $this->config->text[$i];
            $obj->set($step["name"], $data->get_value($step["name"]));
        }

        if ($relation = $this->config->relation_id["db_name"])
            $obj->set($relation, $data->get_value($relation));

        return $obj;
    }

    public function delete($data, $source)
    {
        $sourceData = $source->get_source();
        $obj = $sourceData->get($data->get_id());
        $source->get_source()->delete($obj);
    }

    public function update($data, $source)
    {
        $sourceData = $source->get_source();
        $obj = $sourceData->get($data->get_id());
        $obj = $this->fillModel($obj, $data);
        $sourceData->save($obj);
    }

    public function escape($str)
    {
        throw new Exception("Not implemented");
    }

    public function query($str)
    {
        throw new Exception("Not implemented");
    }

    public function get_new_id()
    {
        throw new Exception("Not implemented");
    }

    protected function getErrorMessage()
    {
        $errors = $this->connection->invalidFields();
        $text = array();
        foreach ($errors as $key => $value) {
            $text[] = $key . " - " . $value[0];
        }
        return implode("\n", $text);
    }
}

?>