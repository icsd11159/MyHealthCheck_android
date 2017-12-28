<?php


class User
{
    function Examines($id_e, $amka , $id_c , $id_d , $type , $name_exam , $result , $date , $comments , $file , $type_f	, $size )
    {
        $this->id_e= $id_e;
        $this->amka = $amka;
        $this->id_c = $id_c;
		$this->id_d = $id_d;
        $this->type = $type;
        $this->name_exam = $name_exam;
        $this->result = $result;
        $this->date = $date;
		$this->comments = $comments;
		$this->file = $file;
		$this->type_f = $type_f;
		$this->size = $size;
		
    }
}