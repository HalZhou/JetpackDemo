### 本Demo 以Jetpack组件库为基础，严格执行MVVM分层架构，使用单Activity应用，构建属于自己的应用框架。其组件库以及用途如下：
1. Compose - UI库
2. Navigation - 导航
3. ViewModel - 屏幕级状态容器
4. Coroutine - 异步
5. Retrofit - 网络请求
6. Room - 数据库
7. DataSource - SharedPreference
8. Hilt - 依赖注入


#### UI Layer
1. Compose or Views
2. ViewModel or Plain Class State holder

#### Domain Layer （负责封装复杂的业务逻辑，或者是多个viewmodel重用的业务逻辑）
1. xxxUseCase 定义可重用的业务逻辑

#### Data Layer（包含应用数据和业务逻辑）
1. xxxRepository 仓库类；每种不同类型的数据定义一个仓库类；对数据源进行处理，包含业务逻辑；
2. xxxDataSource 数据源：网络数据源或本地数据源；


#### 依赖管理 - 版本目录（libs.versions.toml）


### 基础架构
- build-logic 公用插件
- core 核心组件
  - database 数据库
  - datastore SharePreference
  - network 网络