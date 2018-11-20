### Краткое описание
Приложение, основанное на API Яндекс.Переводчик и Яндекс.Словарь. Перевод + словарь, выбор языка, история.
Требует доработки и рефакторинга.

### Функционал
При вводе текста делает запрос к API Яндекс.Переводчик, Яндекс.Словарь. Запрос в словарь производится для пары "ru-en".
При успешном переводе пишет в историю с использованием Json → SharedPreferences. Позможен выбор языка для обоих направлений.
Закладка истории: работает кнопка по очистке истории, остальное в работе (поиска по истории и возможность добавить в избранное пока отсутствуют)

### Использованные технологии
RxJava, Retrofit, Butterknife, Dagger2

<ul>
<img src="https://github.com/rcd27/stasyandex/blob/master/photo_2017-05-12_10-06-20.jpg" width="250">
<img src="https://github.com/rcd27/stasyandex/blob/master/photo_2017-05-12_10-06-11.jpg" width="250">
<img src="https://github.com/rcd27/stasyandex/blob/master/photo_2017-05-12_10-06-24.jpg" width="250">
</ul>

// --- WARNING ---
// FIXME: YANDEX.TRANSLATE API HAS BEEN CHANGED
