# swf4j
轻量级Java Web框架，实现IOC, MVC, AOP特性

* 包结构
    * annotation 注解
        * Action 请求分发 类似@RequestMapping
        * Aspect 切面
        * Controller 控制器
        * Inject 依赖注入 类似@Autowired
        * Service 服务
        * Transaction 事务控制 类似@Transactional
    * bean 框架中使用的到对象
        * Data 数据对象
        * Handler 服务处理对象
        * Param 参数对象
        * Request 服务请求对象
        * View 试图对象
    * helper 助手类
        * AopHelper CGLIB生成代理对象
        * BeanHelper 构建BEAN_MAP
        * ClassHelper 构建CLASS_SET
        * ConfigHelper 读取用户填写的配置文件
        * ControllerHelper 构建ACTION_MAP，实现RequestMapping的功能
        * DatabaseHelper 封装JDBC常用操作 如开启、提交、回滚事务
        * IocHelper IOC相关操作
    * proxy 
        * AspectProxy 代理抽象类 所有增强都要继承这个抽象类
        * Proxy 代理接口
        * ProxyChain 链式代理
        * ProxyManager 封装创建代理对象的方法
        * TransactionProxy 事务代理切面类 在doProxy方法中完成事务控制的相关逻辑
    * util 工具包
        * ClassUtil 类操作工具类
        * CodecUtil 编码与解码操作工具类
        * JsonUtil 使用Jackson实现POJO的序列化与反序列化
        * PropsUtil 读取配置文件工具类
        * ReflectionUtil 反射工具类
        * StreamUtil 流操作工具类
    * ConfigConstant 配置常量
    * DispatcherServlet 继承HttpServlet 整个框架加载完成后本质上就是一个Servlet
    * HelperLoader 封装各Helper类的加载