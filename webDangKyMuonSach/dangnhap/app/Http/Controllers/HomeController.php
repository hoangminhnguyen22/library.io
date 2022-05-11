<?php

namespace App\Http\Controllers;

use App\Models\Borrow;
use App\Models\BorrowDetail;
use Illuminate\Http\Request;
use App\Models\Book;
use Auth;

class HomeController extends Controller
{
    public function index()
    {
//        $borrow = Borrow::where('user_id', '=',Auth::user()->id)->where('borrow_status', 0)->get();
        $borrow = Borrow::where('user_id', '=',Auth::user()->id)
            ->where('borrow_status', 0)
//            ->join('borrowdetail','borrow.borrow_id', '=','borrowdetail.borrow_id')
//            ->join('books','borrowdetail.book_id', '=','books.book_id')
//            ->select('book_title')
            ->get();
        //dd($borrow);
        $bookss = Book::orderBy('book_id','DESc')->get();
        $borrowdetail = BorrowDetail::orderBy('book_id','DESc')->get();
//        $test = Book::find(strval($a));
//        $kt = Auth::user()->id;
//        dd($kt);
        return view('shop',compact('borrow', 'bookss', 'borrowdetail'));
    }
    public function shop()
    {
        $product = Book::orderBy('book_id','DESc')->paginate(15);
//        dd($product);
        return view('user.shop', compact('product'));
    }
    public function shop_cart(){
        return view('user.shop-cart');
    }
    public function check_out(){
        return view('user.check-out');
    }
}
