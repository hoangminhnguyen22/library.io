<?php

use App\Http\Controllers\AdminController;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\AccountController;
use App\Http\Controllers\BookController;
use App\Http\Controllers\BorrowController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CheckOutController;
use App\Http\Controllers\CartController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//Route::get('/', function() {
//    return view('welcome');
//})->name('home');

//Route::get('/', [HomeController::class, 'index'])->name('home.index');
Route::get('/infor', [HomeController::class, 'index'])->name('home.index');
Route::get('/', [HomeController::class, 'shop'])->name('home.shop');
Route::get('/shop-cart',[HomeController::class, 'shop_cart'])->name('home.shop_cart');
Route::get('/checkout',[HomeController::class, 'check_out'])->name('checkout');

Route::get('/admin',[AdminController::class, 'index'])->name('admin.thongke')->middleware(['auth', 'admin']);
Route::get('/admin/dadangky',[AdminController::class, 'dadk'])->name('admin.dadk')->middleware(['auth', 'admin']);
Route::get('/admin/damuon',[AdminController::class, 'damuon'])->name('admin.damuon')->middleware(['auth', 'admin']);
Route::get('/admin/datra',[AdminController::class, 'datra'])->name('admin.datra')->middleware(['auth', 'admin']);

Route::get('/login', [LoginController::class, 'index'])->name('login.index')->middleware('checkLogout');
Route::post('/login', [LoginController::class, 'store'])->name('login.store');
route::get('/logout', [LoginController::class, 'logout'])->name('login.out');

Route::get('/account', [AccountController::class, 'index'])->name('account.index');
Route::get('/book', [BookController::class, 'index'])->name('book.index');
Route::get('/borrow', [BorrowController::class, 'index'])->name('borrow.index');

Route::group(['prefix' => 'cart'], function(){
    Route::get('view', [CartController::class, 'view'])->name('cart.view');
    Route::get('add/{book_id}', [CartController::class, 'add'])->name('cart.add');
    Route::get('remove/{book_id}', [CartController::class, 'remove'])->name('cart.remove');
    Route::get('update/{book_id}', [CartController::class, 'update'])->name('cart.update');
    Route::get('clear', [CartController::class, 'clear'])->name('cart.clear');
});

Route::group(['prefix' => 'checkout'], function(){
    Route::get('/', [CheckOutController::class, 'form'])->name('checkout');
    Route::post('/', [CheckOutController::class, 'submit_form'])->name('checkout');
    //Route::get('/checkoutsuccess', 'CheckOutController@success')->name('checkout.success');
    Route::get('/checkoutsuccess', [CheckOutController::class, 'success'])->name('checkout.success');
});
