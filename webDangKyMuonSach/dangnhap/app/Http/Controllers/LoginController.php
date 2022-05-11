<?php

namespace App\Http\Controllers;

use App\Models\user;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class LoginController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('admin.index');
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $request -> validate([
            'username' => 'required',
            'password' => 'required'
        ],[
            'username.required' => 'không để trống tên đăng nhập',
            'password.required' => 'không để trống mật khẩu'
        ]);

        $arr = [
            'username' => $request ->username,
            'password' => $request ->password,
        ];

        //dd($request->all());
        //dd($arr);

        if(Auth::attempt($arr)){
            return redirect()->route('home.shop');
        }
        else{
            return redirect()->route('login.index')->with('error','Đăng nhập thất bại');
        }
    }

    public function logout(){
        Auth::logout();
        return redirect()->route('login.index');
    }
}
