<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Helper\CartHelper;
use App\Models\Book;

class CartController extends Controller
{
    public function view(){
        return view('user.shop-cart');
    }

    public function add(CartHelper $cart, $book_id){
//        $book = Book::all()->find("1");
//        $book = Book::whereIn('book_id', [$book_id])->get();
//        $book = $books->find($book_id);
//        $book = Book::find("$book_id");
        $book = Book::where('book_id', '=', $book_id)->first();
        //dd($book);
        $cart->add($book);
        //dd(session('cart'));
        return redirect()->back();
    }

    public function remove(CartHelper $cart, $book_id){
        $cart->remove($book_id);
        //dd(session('cart'));
        return redirect()->back();
    }

    public function update(CartHelper $cart, $book_id){
        $quantity = request()->quantity ? request()->quantity : 1;
        $cart->update($book_id, $quantity);
        //dd(session('cart'));
        return redirect()->back();
    }

    public function clear(CartHelper $cart){
        $cart->clear();
        //dd(session('cart'));
        return redirect()->back();
    }
}
