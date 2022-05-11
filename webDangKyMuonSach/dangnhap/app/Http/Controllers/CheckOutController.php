<?php

namespace App\Http\Controllers;

use App\Models\Borrow;
use App\Models\BorrowDetail;
use Illuminate\Http\Request;
use App\Helper\CartHelper;
use App\Controller\CartController;
use Auth;

class CheckOutController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth');
    }

    public function form(CartHelper $cart){
//        dd($cart->items);
        $dem = count($cart->items);
        // if(count($cart->items)==0){
        if(empty($cart->items)){
            return redirect()->back()->with('error','bạn chưa thêm sản phẩm vào giỏ hàng');
        }else{
            return view('user.check-out');
        }
    }

    public function submit_form(Request $req, CartHelper $cart){

        $c_id = Auth::user()->id;
        if($borrow = Borrow::create([
            'user_id' => $c_id,
            'borrow_returndate'=> $req -> borrow_returndate,
        ])){
            $order_id = $borrow->id;
            foreach($cart->items as $book_id => $item){
                BorrowDetail::create([
                    'borrow_id'=>$order_id,
                    'book_id'=>$book_id,
                ]);
            }

            session(['cart'=>'']);
            return redirect()->route('home.shop')->with('success','Mượn sách thành công, mã mượn sách của bạn là '.$order_id);
        }
        else{
            return redirect()->route('home.shop')->with('danger','Đặt hàng không thành công');
        }

    }
}
