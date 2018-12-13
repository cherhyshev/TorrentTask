# Torrent
* На трекере хранится список файлов и информация об активных пользователях, у которых есть те или иные файлы (возможно не целиком).
* С помощью клиентского приложения можно просматривать список файлов на трекере, а также добавлять новые и выбирать файлы из списка для скачивания.
* Файлы условно разбиваются на последовательные блоки бинарных данных константного размера (например 10M). Последний блок может иметь меньший размер. Блоки нумеруются с нуля.
---
# Torrent
* Клиент при подключении отправляет на трекер список раздаваемых им файлов.
* При скачивании файла клиент получает у трекера информацию о клиентах, раздающих файл (сидах), и далее общается с ними напрямую.
* У отдельного сида можно узнать о том, какие полные части у него есть, а также скачать их.
* После скачивания отдельных блоков некоторого файла клиент становится сидом.
---
# Torrent-tracker
* Хранит мета-информацию о раздаваемых файлах:
    * идентификатор
    * активные клиенты (недавно был update), у которых есть этот файл целиком или некоторые его части
    
* Порт сервера: 8081
    
* Запросы:
    * list — список раздаваемых файлов
    * upload — публикация нового файла
    * sources — список клиентов, владеющих определенным файлов целиком или некоторыми его частями
    * update — загрузка клиентом данных о раздаваемых файлах 
    
---
# List

Формат запроса:
    <1: Byte>

Формат ответа:
    <count: Int> (<id: Int> <name: String> <size: Long>)*,
    count — количество файлов
    id — идентификатор файла
    name — название файла
    size — размер файла
    
---
# Upload

Формат запроса:
    <2: Byte> <name: String> <size: Long>,
    name — название файла
    size — размер файла

Формат ответа:
    <id: Int>,
    id — идентификатор файла
    
# Примечание
* Если клиент А и клиент Б решили опубликовать файл abc.txt, то это будут **разные** файлы, иными словами каждый запрос на публикацию файла возвращает **новый** id
---

# Sources

Формат запроса:
    <3: Byte> <id: Int>,
    id — идентификатор файла

Формат ответа:
    <size: Int> (<ip: ByteByteByteByte> <clientPort: Short>)*,
    size — количество клиентов, раздающих файл
    ip — ip клиента,
    clientPort — порт клиента

---
# Update
Формат запроса:
    <4: Byte> <clientPort: Short> <count: Int> (<id: Int>)*,
    clientPort — порт клиента,
    count — количество раздаваемых файлов,
    id — идентификатор файла

Формат ответа:
    <updated: Boolean>,
    updated — True, если информация успешно обновлена
    
# Примечание
* Клиент обязан исполнять данный запрос каждые 5 минут, иначе сервер считает, что клиент ушел с раздачи
---
# Torrent-client
* Порт клиента указывается при запуске и передается на трекер в рамках запроса update
* Каждый файл раздается по частям, размер части — константа на всё приложение
* Клиент хранит и раздает эти самые части
* Запросы:
    * stat — доступные для раздачи части определенного файла
    * get — скачивание части определенного файла
    
---
# Stat
Формат запроса:
    <1: Byte> <id: Int>,
    id — идентификатор файла

Формат ответа:
    <count: Int> (<part: Int>)*,
    count — количество доступных частей
    part — номер части
    
# Примечание
* Часть считается доступной для раздачи, если она хранится на клиенте целиком
---
# Get
Формат запроса:
    <2: Byte> <id: Int> <part: Int>
    id — идентификатор файла,
    part — номер части 

Формат ответа:
    <content: Bytes>,
    content — содержимое части

---
# Требования:
* Maven/Gradle проект
* Консольные трекер и клиент, позволяющие исполнять указанные запросы
* Тесты
* Документация процесса сборки артефактов вашего приложения
  * В идеале, хотелось бы, чтобы этими артефактами были два shell-скрипта
  (один для запуска клиента, другой - для сервера)
  * Однако двух executable jar-файлов будет тоже достаточно
* Клиент должен сохранять информацию о раздаваемых файлах между перезапусками
* Трекер должен сохранять список раздаваемых файлов между перезапусками
---
# Примечания:
* Разрешается использовать библиотеки для упрощения ввода-вывода
* Рекомендуется взглянуть на DataInputStream и DataOutputStream
* То, как пишутся и читаются данные из потока, определяется реализацией DataInputStream и DataOutputStream
* Для передачи String используется алгоритм DataOutputStream.writeUTF
* IP адреса передаются как четыре последовательных байта: 127.0.0.1 -> 127/0/0/1
Срок: <b>17.12.2018 23:59</b>
---
# Формат сдачи
* Каждое задание выполняете в отдельной ветке в репозитории на GitHub
* Создаете pull request ветки в master этого же репозитория
* Тема PR: Java06. ДЗ 05, &lt;фамилия&gt; &lt;имя&lt;
* В комментарии упоминаете username преподавателя (@sproshev, @dsavvinov)
* Посылаете письмо преподавателю с такой же темой с ссылкой на pull request
Проверяйте табличку на странице курса на предмет того, что преподаватель увидел ваш PR