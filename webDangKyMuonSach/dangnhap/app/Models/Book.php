<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Book extends Model
{
    use HasFactory;
    protected  $table = 'books';
    protected $fillable = ['book_id', 'book_title', 'image', 'book_author', 'book_status'];

    public function borrowdetail()
    {
        return $this->belongsTo(BorrowDetail::class, 'book_id', 'book_id');
    }
}
