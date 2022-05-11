@extends('layouts.admin')

@section('title', 'Product List')
@section('main')

    <div class="row">
        <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
                <div class="inner">
                    <h3>{{$borrows->count()}}</h3>

                    <p>New Orders</p>
                </div>
                <div class="icon">
                    <i class="ion ion-bag"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
                <div class="inner">
                    <h3>{{$books->count()}}</h3>

                    <p>Number of Book</p>
                </div>
                <div class="icon">
                    <i class="ion ion-stats-bars"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-warning">
                <div class="inner">
                    <h3>{{$users->count()}}</h3>

                    <p>User Registrations</p>
                </div>
                <div class="icon">
                    <i class="ion ion-person-add"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->

        <!-- ./col -->
    </div>

    <div class="card-body">
        <!-- Default panel contents -->
        <form action="{{route('admin.thongke')}}" method="GET" role="form">
            <div class="card-header ui-sortable-handle" style="cursor: move;">
                <div class="row">
                    <div class="col-sm-4">
                        <!-- select -->
                        <div class="card-title">
                            <label>Từ ngày</label>
                            <input class="form-control" type="date" class="form-control" name="date_from" placeholder="Input field">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card-title">
                            <label>Đến ngày</label>
                            <input class="form-control" type="date" class="form-control" name="date_to" placeholder="Input field">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card-tools">
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <br>
        <div class="card-header ui-sortable-handle" style="cursor: move;">
            <h3 class="card-title">
                <i class="fas fa-chart-pie mr-1"></i>
                Đơn mượn
            </h3>
            <div class="card-tools">
                <ul class="nav nav-pills ml-auto">
                    <li class="nav-item">
                    @if($flag == 0)
                        <a class="nav-link active" href="{{route('admin.dadk')}}">Đã đăng ký</a>
                    @else
                        <a class="nav-link" href="{{route('admin.dadk')}}">Đã đăng ký</a>
                    @endif
                    </li>
                    <li class="nav-item">
                        @if($flag == 1)
                            <a class="nav-link active" href="{{route('admin.damuon')}}">Đã lấy sách</a>
                        @else
                            <a class="nav-link" href="{{route('admin.damuon')}}">Đã lấy sách</a>
                        @endif
                    </li>
                    <li class="nav-item">
                        @if($flag == 2)
                            <a class="nav-link active" href="{{route('admin.datra')}}">Đã trả sách</a>
                        @else
                            <a class="nav-link" href="{{route('admin.datra')}}">Đã trả sách</a>
                        @endif
                    </li>
                </ul>
            </div>
        </div>
        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th>Mã đơn</th>
                <th>Người mượn</th>
                <th>Số sách</th>
                <th>Tên sách</th>
                <th>Trạng thái</th>
                <th>Ngày đăng ký Mượn</th>
                <th>Ngày trả</th>
            </tr>
            </thead>
            <tbody>
            @foreach($borrowdate as $borrow)
            <tr>
                <td>{{$borrow->borrow_id}}</td>
                <td>{{$borrow->account->user_name}}</td>
                <td>{{$borrow->borrowdetail->count()}}</td>
                <td>
                    @foreach($borrow->borrowdetail as $IDbookBD)
                        @foreach($books as $bookB)
                            @if($IDbookBD->book_id == $bookB->book_id)
                                {{$bookB->book_title}}<br>
                            @endif
                        @endforeach
                    @endforeach
                </td>
                <td>
                @if($borrow->borrow_status==0)
                    <span class="badge badge-danger">đã đăng kí</span>
                @elseif($borrow->borrow_status==1)
                    <span class="badge badge-warning">đã lấy sách</span>
                @else
                    <span class="badge badge-success">đã trả sách</span>
                @endif
                </td>
                <td>{{$borrow->borrow_begindate}}</td>
                <td>{{$borrow->borrow_returndate}}</td>
            </tr>
            @endforeach
            </tbody>
        </table>
    </div>
@stop
