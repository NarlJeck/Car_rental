<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Прокат авто</title>
</head>
<body>
<header>
    <h1>Прокат авто</h1>
    <nav>
        <button onclick="alert('Вы выбрали марку: Toyota')">Toyota</button>
        <button onclick="alert('Вы выбрали марку: Ford')">Ford</button>
        <button onclick="alert('Вы выбрали марку: BMW')">BMW</button>
        <button onclick="alert('Вы выбрали марку: Mercedes')">Mercedes</button>
    </nav>
</header>

<main>
    <h2>Доступные автомобили</h2>
    <ul>
        <li>
            <a href="#" title="Toyota Camry - комфортабельный седан">Toyota Camry</a>: Надежный и комфортный седан для городских поездок.
        </li>
        <li>
            <a href="#" title="Ford Focus - хэтчбек с отличной управляемостью">Ford Focus</a>: Хэтчбек с отличной управляемостью и экономичным расходом топлива.
        </li>
        <li>
            <a href="#" title="BMW 3 Series - спортивный седан">BMW 3 Series</a>: Спортивный седан с великолепной динамикой и высоким уровнем комфорта.
        </li>
        <li>
            <a href="#" title="Mercedes-Benz C-Class - элегантный и премиум авто">Mercedes-Benz C-Class</a>: Элегантный и премиум седан с множеством функций.
        </li>
    </ul>
</main>
</body>
</html>