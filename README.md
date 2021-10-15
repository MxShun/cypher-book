# cypher-book
Cypher: Spring BootCamp - Book API

### システム要件
常に最新のバージョンを利用してください

次は2021/9時点のバージョン

- Spring Boot 2.5.4
- Kotlin 1.5.31
- Java 11 以上
- Gradle 7以上
- その他必要なライブラリ

spring-boot-starter-webをdependenciesに設定してください

### Book API
書籍管理API。CRUDの勉強用です。


| No | 課題 | やること |
| --- | --- | --- |
| 1 | Databaseの準備 | 1. docker-composeでpostgresqlを用意してください<br />2. docker-composeを利用します (dockerが難しい場合は模範解答を提供します)<br />3. 起動時にtable createと初期データをセットしてください<br />4. table 定義 (schema名はなんでも🙆‍♂️)<br /><br />CREATE TABLE main.book(<br />    isbn       varchar(13)  NOT NULL,<br />    title      varchar(100) NOT NULL,<br />    author     varchar(100) NOT NULL,<br />    publisher  varchar(100) NOT NULL,<br />    price      integer      NOT NULL,<br />    created_at timestamp    NOT NULL,<br />    updated_at timestamp    NOT NULL,<br />    PRIMARY KEY (isbn)<br />) |
| 2 | List実装 | 1. Databaseに登録されているデータをJsonで返却するlist処理を実装してください<br />2. Database AccessにはJdbcTemplateを使ってください<br />3. Databaseへの接続パスワードは環境変数で設定できるようにしてください<br />4. データが存在しない場合は空List Jsonを返却してください。HTTP Statusは200を返却してください<br />5. Database接続等の例外が発生した場合は HTTP Status 500でJsonを返却してください<br />6. 実装イメージ  (一部省略) この通りに作らなくてもOKです<br /><br />└── src<br />    └── main<br />        └── kotlin<br />            └── com<br />                └── example<br />                    └── app<br />                        ├── ExampleApplication.kt<br />                        ├── repository<br />                        │   └── BookRepository.kt (JdbcTemplate実装を記述する)<br />                        └── web<br />                            ├── controller<br />                            │   └── BookController.kt<br />                            └── entity<br />                                └── Book.kt|<br /> |
| 3 | Clean Architecture | 1. gradleをマルチプロジェクト構成にしてアプリケーションをクリーンアーキテクチャにしてください (難しい場合はヘルプします)<br />2. DatabaseとのコミュニケーションとController Responseはdomain objectを使わずにそれぞれdtoを利用してください<br />3. 構成<br />  a. web (Controller / SpringBootApplication起動クラス)<br />  b. domain (domain object)<br />  c. infra (databaseとのコミュニケーション)<br />  d. application (usecase / infraのinterface)<br />4. 実装イメージ (一部省略 / class名などは適宜変更してください) <br /> |
| 4 | MyBatis導入 | JdbcTemplateの実装をMyBatisに変更してください |
| 5 | Get実装 | 1. isbnを引数で受け取ってBookResponseを返却する処理を実装してください<br />2. isbnは13桁の数字だけvalidとしてください<br />3. dataが見つからない場合は HTTP status 404にして次のようなJSONを返却してください。<br />  例: {"reason":"No Book found. isbn=9784111111111"} |
| 6 | Value Object | 1. isbnのvalue objectを作成してください。換言すると、domain layerにIsbn Classを作ってください、という意味です<br />2. #5 で作成したGet処理のリクエストisbn に、チェックディジット計算のチェックを導入してください。<br />3. チェックディジットが不正の場合は HTTP status 400にしてJsonを返却してください<br />4. isbnのチェックディジット計算仕様は適当に検索して拾ってください。例えばこことか<br />5. チェックディジット計算のfunctionはJUnitで単体テストを書いてください |
| 7 | Database例外ハンドリング | 1. List, Get処理でDatabaseAccessExceptionが発生した場合の処理を実装してください<br />2. HTTP status 500にしてJSONを返却してください。<br />  例: {"reason":"BookRepository#findでエラーが発生しました"} |
| 8 | Insert実装 | 新規の書籍を登録する処理を自由に実装してください |
| 9 | Update実装 | 書籍情報を更新する処理を自由に実装してください |
| 10 | Response Logger実装 | Requestで受け取った情報 (Request Header, Method, URI, Body) をdebug logに出力してください |
| 11 | custom validator | 1. publisherが 「ほげほげ書房」 のとき、priice < 1000 の商品は登録、更新できないようにしてください<br />  a. このvalidateはWeb Layerに実装してください |
| 12 | gzip response | APIのResponseをgzip 圧縮してください |
| 13 | API Call | 1. Get時にBookのRatingとGenreを返すAPIをCallしてレスポンスに追加してください<br />a. APIは自作してもMockを用意してもかまいません<br />b. APIはISBNを受け取り、Rating, Genreを返します。例: { "rating": "4.5", "genre": "少年コミック" }<br />c. API Callに失敗した場合、rating, genreをつけずに従来のGet処理のResponseを返すようにしてください |
| おまけ1 | aws s3にcsv fileをuploadする | 1. listの結果をcsvにしてs3にuploadしてください<br />2. s3はlocalstackを利用してください |
| おまけ2 | Response Logger実装 | Responseをdebug logに出力してください |
| おまけ3 | 認証認可 | 1. Insert, Update処理に対して指定されたユーザ以外は処理を行えないようなセキュリティ機構を実装してください<br />2. 実装、処理方法、利用するリソースはすべて自由です 🙆‍♂️ |
| おまけ4 | 並列実行 | 1. Get時のAPI CallとDB処理を並列実行してください<br />2. この時点ではDB処理はBlocking IOになります。APIはNon-BlockingでもBlockingでも 🙆‍♂️ |
| おまけ5 | WebFlux | 1. 課題で作ったAPIをWebFluxで実装し直します<br />2. BlockHound を入れて、エラーにならなければ 🙆‍♂️<br />3. Controllerはアノテーション形式でもfunction形式でも🙆‍♂️ |
