package com.example/*
length	thuộc tính trả về chiều dài của chuỗi
indexOf(chuỗi)	Trả về vị trí đầu tiên tìm thấy, không tìm thấy trả về -1
lastIndexOf(chuỗi)	lastIndexOf trả về vị trí cuối cùng tìm thấy
contains(chuỗi con)	Contains Kiểm tra chuỗi con có nằm trong chuỗi s?
subString(vt)	Trích lọc toàn bộ bên phải chuỗi từ vt
subString(startIndex,endIndex)	Trích lọc giữa chuỗi nằm giữa start tới end index
replace(chuỗi cũ, chuỗi mới)	Đổi toàn bộ chuỗi cũ thành chuỗi mới
replaceFirst(chuỗi cũ, chuỗi mới)	Đổi chuỗi cũ thành chuỗi mới nhưng chỉ áp dụng cho chuỗi cũ đầu tiên
trimStart	xóa khoảng trắng dư thừa bên trái
trimEnd	xóa khoảng trắng dư thừa bên phải
trim	xóa khoảng trắng dư thừa bên trái và phải
compareTo(chuỗi s2)
So sánh 2 chuỗi có phân biệt chữ HOA và chữ thường
=0 khi s1=s2

>0 khi s1>s2

<0 khi s1<s2

compareTo(s2, ignoreCase = true)	So sánh 2 chuỗi KHÔNG phân biệt chữ HOA và chữ thường
=0 khi s1=s2

>0 khi s1>s2

<0 khi s1<s2

 plus(chuỗi x)	 Nối chuỗi x vào chuỗi gốc
 split(chuỗi tách)	 Tách chuỗi gốc thành List<String>
 toUpperCase	 Chuyển chuỗi thành IN HOA
 toLowerCase	 Chuyển chuỗi thàh in thường
 */
/*Hàm IndexOf – trả về vị trí đầu tiên tìm thấy, nếu không thấy trả về -1:*/
/*fun main(args: Array) {
    var s:String="Obama"
    var i = 0
// Trả về i = 2
    i = s.indexOf("a")
    println(i)
}*/
