<?php
namespace App\Helper;

class CartHelper
{
    public $items = [];
    public $total_quantity = 0;

    public function __construct()
    {
        $this->items = session('cart') ? session('cart'): [];
        $this->total_quantity = $this->get_total_quantity();

    }

    public function add($product, $quantity = 1)
    {
        $item=[
            'book_id' => $product -> book_id,
            'book_title' => $product -> book_title,
            'book_author' => $product -> book_author,
            'image' => $product -> image,
            'quantity' => $quantity,
        ];
        //dd($item);
        if(isset($this -> items[$product -> book_id])){
            $this -> items[$product -> book_id]['quantity'] = $quantity;
        }else{
            $this -> items[$product -> book_id] = $item;
        }
        session(['cart' => $this->items]);
        // dd(session);
    }

    public function remove($book_id)
    {
        if(isset($this -> items[$book_id])){
            unset($this -> items[$book_id]);
        }
        session(['cart' => $this->items]);
    }

    public function update($book_id, $quantity = 1)
    {
        if(isset($this -> items[$book_id])){
            $this -> items[$book_id]['quantity'] = $quantity;
        }
        session(['cart' => $this->items]);
    }

    public function clear(){
        session(['cart' => '']);
    }

    private function get_total_quantity(){
        $t =0;
        foreach ($this->items as $item)
        {
            $t += $item['quantity'];
        }
        return $t;
    }
}
?>
