@extends('layouts.site')

@section('main')
<table class="table">
    <thead>
    <tr>
        <th>Mã mượn của bạn</th>
        <th>số sách của đơn này</th>
        <th>Mã sách bạn mượn</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    @foreach($borrow as $bor)
    <tr>
        <td>{{$bor->borrow_id}}</td>
        <td>{{$bor->borrowdetail->count()}}</td>
        @foreach($bor->borrowdetail as $book)
            <td>{{$book->book_title}}</td></br>
        @endforeach
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

@stop();
