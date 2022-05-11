<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Auth;

class adminMiddleware
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
        if(Auth::check()){
            if(Auth::user()->level==1){
                return $next($request);
                // return redirect()->route('admin.dashboard');
                // return redirect()->intended('admin');
            }
            if(Auth::user()->level==2){
                return $next($request);
                // return redirect()->route('admin.dashboard');
                // return redirect()->intended('admin');
            }
        }
        return redirect()->route('home.shop');
    }
}
