<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Borrow extends Model
{
    use HasFactory;
    const UPDATED_AT = null;
    const CREATED_AT = null;
    protected $table = 'borrow';

    protected $fillable = ['borrow_id', 'user_id', 'borrow_status', 'borrow_begindate', 'borrow_returndate' ];


    //thử hasOne hoặc hasMany
    public function account()
    {
        return $this->hasOne(Account::class, 'id', 'user_id');
    }

    public function borrowdetail()
    {
        return $this->hasMany(BorrowDetail::class, 'borrow_id', 'borrow_id');
    }
}
