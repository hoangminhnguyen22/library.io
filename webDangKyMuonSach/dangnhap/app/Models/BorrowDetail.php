<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class BorrowDetail extends Model
{
    use HasFactory;
    const UPDATED_AT = null;
    const CREATED_AT = null;
    protected $table = 'borrowdetail';

    protected $fillable = ['borrow_id', 'book_id'];

    public function book()
    {
        return $this->hasMany(Book::class, 'book_id', 'book_id');
    }

    public function borrow()
    {
        return $this->belongsTo(Borrow::class, 'borrow_id', 'borrow_id');
    }
}
