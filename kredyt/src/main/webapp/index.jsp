<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Oblicz raty kredytu</title>
</head>
<body>
    <h1>Wylicz raty kredytu</h1>
    <form action='kredyt' method="post">
        <label>Wnioskowana kwota kredytu:<input type="text" id="kwota" name="kwota"/><br /></label>
        <label>Ilosc rat:<input type="text" id="ilosc" name="ilosc"/><br /></label>
        <label>Oprocentowanie:<input type="text" id="oprocentowanie" name="oprocentowanie"/><br /></label>
        <label>Oplata stala:<input type="text" id="oplata" name="oplata"/><br /></label>
        <label>Rodzaj rat (malejaca, stala):<input type="text" id="rodzaj" name="rodzaj"/><br /><br /></label>
        <input type="submit" value="Wylicz raty"/>
    </form>
</body>
</html>