<?php

namespace App\Http\Controllers;

use App\Models\Borrow;
use App\Models\BorrowDetail;
use App\Models\User;
use Illuminate\Http\Request;
use App\Models\Book;
use Auth;

class AdminController extends Controller
{
    public function index()
    {
        $books = Book::orderBy('book_id', 'DESC')->get();
        $borrows = Borrow::orderBy('borrow_id', 'DESC')/*->where('borrow_status', 0)*/->get();
        $users = User::orderBy('id', 'DESC');
        $flag=9;
        if(request()->date_from && request()->date_to){
            $borrowdate = Borrow::/*where('borrow_status', 0)
                ->*/whereBetween('borrow_begindate',[request()->date_from, request()->date_to])
                ->get();
        }else{
            $borrowdate = Borrow::orderBy('borrow_id', 'DESC')/*->where('borrow_status', 0)*/->get();
        }
        return view('admin.thongke', compact('books', 'borrows','users','borrowdate',  'flag'));
    }
    public function dadk()
    {
        $books = Book::orderBy('book_id', 'DESC')->get();
        $borrows = Borrow::orderBy('borrow_id', 'DESC')->where('borrow_status', 0)->get();
        $borrowdate = Borrow::where('borrow_status', 0)->orderBy('borrow_id', 'DESC')
            ->get();
        $flag = 0;
        $users = User::orderBy('id', 'DESC');
        return view('admin.thongke', compact('books', 'borrows', 'borrowdate','users', 'flag'));
    }
    public function damuon()
    {
        $books = Book::orderBy('book_id', 'DESC')->get();
        $borrows = Borrow::orderBy('borrow_id', 'DESC')->where('borrow_status', 1)->get();
        $users = User::orderBy('id', 'DESC');
        $flag = 1;
        $borrowdate = Borrow::where('borrow_status', 1)->orderBy('borrow_id', 'DESC')
            ->get();
        return view('admin.thongke', compact('books', 'borrows', 'borrowdate','users', 'flag'));
    }
    public function datra()
    {
        $books = Book::orderBy('book_id', 'DESC')->get();
        $borrows = Borrow::orderBy('borrow_id', 'DESC')->where('borrow_status', 2)->get();
        $users = User::orderBy('id', 'DESC');
        $borrowdate = Borrow::where('borrow_status', 2)
            ->get();
        $flag = 2;
        return view('admin.thongke', compact('books', 'borrows', 'borrowdate','users', 'flag'));
    }
}
