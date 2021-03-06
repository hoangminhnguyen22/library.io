@extends('layouts.site')

@section('main')

<!-- Hero Section Begin -->
<section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>Book list</span>
                        </div>
                        <ul>

                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="#">
                                <div class="hero__search__categories">
                                <i class="fa fa-bars"></i>
                                    All Categories
                                    <span class="arrow_carrot-down"></span>
                                </div>
                                <input type="text" placeholder="What do yo u need?">
                                <button type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+65 11.188.888</h5>
                                <span>support 24/7 time</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="{{URL::asset('site')}}/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Information</h2>
                        <div class="breadcrumb__option">
                            <a href="">Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-5">
                    <div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Department</h4>
                            <ul>
                                <li><a href=""></a>Ch??nh tr??? ??? ph??p lu???t</li>
                                <li><a href=""></a>Khoa h???c c??ng ngh??? ??? Kinh t???</li>
                                <li><a href=""></a>V??n h???c ngh??? thu???t</li>
                                <li><a href=""></a>V??n h??a x?? h???i ??? L???ch s???</li>
                                <li><a href=""></a>Gi??o tr??nh</li>
                                <li><a href=""></a>Truy???n, ti???u thuy???t</li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="col-lg-9 col-md-7">
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            @if(Session::has('success'))
                                <div class="alert alert-success">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    {{Session::get('success')}}
                                </div>
                            @endif
                            <h2>th??ng tin m?????n s??ch</h2>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>M?? m?????n c???a b???n                       </th>
                                <th>s??? s??ch c???a ????n n??y                   </th>
                                <th>M?? s??ch b???n m?????n                      </th>
                                <th>Action                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            @foreach($borrow as $bor)
                                <tr>
                                    <td>{{$bor->borrow_id}}</td>
                                    <td>{{$bor->borrowdetail->count()}}</td>
                                    <td>
                                        @foreach($bor->borrowdetail as $IDbookBD)
                                            @foreach($bookss as $bookB)
                                                @if($IDbookBD->book_id == $bookB->book_id)
                                                    {{$bookB->book_title}}<br>
                                                @endif
                                            @endforeach
                                        @endforeach
                                    </td>
                                    <td class="text-right">

                                        <a href="" class="btn btn-sm btn-success">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <a href="" class="btn btn-sm btn-danger btndelete">
                                            <i class="fas fa-trash"></i>
                                        </a>

                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

@stop();
