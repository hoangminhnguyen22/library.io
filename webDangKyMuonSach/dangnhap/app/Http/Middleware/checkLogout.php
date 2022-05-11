<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Auth;

class checkLogout
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle(Request $request, Closure $next)
    {
        // if(Auth::check()){
        //     if(Auth::user()->role_id==1){
        //         return redirect()->route('admin.dashboard');
        //         // return redirect()->intended('admin');
        //     }
        //     if(Auth::user()->role_id==2){
        //         return redirect()->route('admin.dashboard');
        //     }
        //     if(Auth::user()->role_id==3){
        //         return redirect()->route('home.index');
        //         //return redirect()->intended('admin');
        //     }
        // }
        if(Auth::check()){
            return redirect()->route('home.shop');
            // return $next($request);
        }
        return $next($request);
    }
}
