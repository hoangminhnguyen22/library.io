<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Account extends Model
{
    use HasFactory;
    protected $table = 'users';

    protected $fillable = ['id', 'user_name', 'user_phone', 'level', 'username', 'password'];

    public function borrow()
    {
        return $this->hasOne(Borrow::class, 'user_id', 'id');
    }
}
