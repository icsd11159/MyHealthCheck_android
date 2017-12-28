<?php

/**
 * Created by PhpStorm.
 * User: Dr. Shqpitejia
 * Date: 31/5/2015
 * Time: 8:39 μμ
 */
class Client
{
    function Client($id_c,$cname,$csurname, $amka, $mail, $history)
    {
	    $this->id_c=$id_c;
        $this->cname = $cname;
        $this->csurname = $csurname;
        $this->amka = $amka;
        $this->mail = $mail;
		$this->history = $history;

        
    }
}

?>