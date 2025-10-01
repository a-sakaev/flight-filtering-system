# Flight Filtering Module

## 📌 Описание
Тестовое задание: реализовать модуль фильтрации авиаперелётов.  
Перелёт состоит из одного или нескольких сегментов. Сегмент описывается временем **вылета** и **прилёта**.

Модуль позволяет применять различные правила фильтрации для списка перелётов.

## 🚀 Реализованные фильтры
1. **Вылет до текущего момента времени**  
   Исключаются перелёты, где сегмент начинается в прошлом.

2. **Сегменты с датой прилёта раньше даты вылета**  
   Исключаются некорректные перелёты.

3. **Перелёты с общим временем на земле более 2 часов**  
   Если между сегментами пассажир ждёт больше 2 часов, перелёт исключается.

## 🛠 Структура проекта
Пакет: `com.gridnine.testing`

Классы:
- `Main` — точка входа в программу (демонстрация работы фильтров).
- `Flight` / `Segment` — модельные классы перелёта и сегмента.
- `FlightBuilder` — фабрика для генерации тестовых данных.
- `FlightFilter` — интерфейс фильтра.
- `DepartureBeforeNowFilter` — фильтр по вылету до текущего момента.
- `ArrivalBeforeDepartureFilter` — фильтр по прилёту раньше вылета.
- `ExcessiveGroundTimeFilter` — фильтр по времени на земле.
- `FlightFilteringModule` — класс для применения одного или нескольких фильтров.

## ▶️ Пример запуска
Для запуска используйте метод `main()` в классе `Main`.

Пример вывода в консоль:
------------Перелеты------------
Flight 1: [2025-10-01T10:00 → 2025-10-01T14:00]
Flight 2: [2025-09-30T20:00 → 2025-10-01T02:00]

Фильтр перелетов до текущего времени:
[ ...]

Фильтр перелетов c вылетом раньше прилета:
[ ...]

Фильтр перелетов с суммарным временем на земле более 2 часов:
[ ...]

------Применение всех фильтров------
[ ... ]

## 📂 Запуск
1. Склонировать репозиторий:
   ```bash
   git clone https://github.com/username/flight-filtering.git
2. Собрать и запустить проект (например, через IntelliJ IDEA или Maven/Gradle).
3. Выполнить Main.main().

## 🧩 Как добавить свой фильтр
Чтобы добавить новый фильтр:
1. Создайте класс, реализующий интерфейс FlightFilter.
2. Определите условие отбора перелётов.
3. Передайте фильтр в FlightFilteringModule.

Пример: фильтр по количеству сегментов

 ```java
package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class MaxSegmentsFilter implements FlightFilter {
    private final int maxSegments;

    public MaxSegmentsFilter(int maxSegments) {
        this.maxSegments = maxSegments;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getSegments().size() <= maxSegments)
                .collect(Collectors.toList());
    }
}
```

Использование фильтра

```java
FlightFilter maxSegmentsFilter = new MaxSegmentsFilter(2);
List<Flight> filtered = FlightFilteringModule.filter(flights, maxSegmentsFilter);
